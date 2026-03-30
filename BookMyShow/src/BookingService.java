import java.util.*;

public class BookingService {

    SeatLockService seatLockService;
    PricingService pricingService;
    PaymentService paymentService;

    public BookingService(SeatLockService s, PricingService p, PaymentService pay) {
        this.seatLockService = s;
        this.pricingService = p;
        this.paymentService = pay;
    }

    public Booking bookTicket(Customer user, Show show, List<Seat> seats) {

        List<String> seatIds = new ArrayList<>();
        for (Seat s : seats) seatIds.add(s.id);

        if (!seatLockService.lockSeats(show.id, seatIds)) {
            throw new RuntimeException("Seats not available");
        }

        double total = 0;
        for (Seat seat : seats) {
            total += pricingService.getPrice(show, seat);
        }

        if (!paymentService.processPayment(user.id, total)) {
            seatLockService.unlockSeats(show.id, seatIds);
            throw new RuntimeException("Payment failed");
        }

        Booking booking = new Booking();
        booking.id = UUID.randomUUID().toString();
        booking.user = user;
        booking.show = show;
        booking.totalPrice = total;
        booking.status = BookingStatus.CONFIRMED;
        booking.payment = new Payment(UUID.randomUUID().toString(), total);

        for (Seat seat : seats) {
            show.seatStatusMap.put(seat.id, SeatStatus.BOOKED);
            booking.tickets.add(new Ticket(UUID.randomUUID().toString(), seat, show));
        }

        seatLockService.unlockSeats(show.id, seatIds);

        return booking;
    }
}
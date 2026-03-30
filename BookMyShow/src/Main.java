import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Users
        Admin admin = new Admin("A1", "AdminUser", "admin@mail.com");
        Customer user = new Customer("U1", "Kavya", "kavya@mail.com");

        // Admin Service
        AdminService adminService = new AdminService();

        // 🎬 Add Movie
        Movie movie = new Movie("M1", "Inception");
        adminService.addMovie(admin, movie);

        // Add Theatre
        Theatre theatre = new Theatre("T1", "PVR", "Bangalore");
        adminService.addTheatre(admin, theatre);

        // 🎭 Create Screen + Seats
        Screen screen = new Screen("S1");
        Seat s1 = new Seat("A1", SeatType.GOLD);
        Seat s2 = new Seat("A2", SeatType.SILVER);

        screen.seats.add(s1);
        screen.seats.add(s2);
        theatre.screens.add(screen);

        // Add Show
        Show show = new Show("SH1", movie, screen);
        adminService.addMovieShow(admin, show);

        // Services for booking
        SeatLockService lockService = new SeatLockService();
        PricingService pricingService = new PricingService();
        PaymentService paymentService = new PaymentService();

        BookingService bookingService =
                new BookingService(lockService, pricingService, paymentService);

        //Book Tickets
        Booking booking = bookingService.bookTicket(user, show, Arrays.asList(s1, s2));

        // Output
        System.out.println("\n--- BOOKING DETAILS ---");
        System.out.println("Booking ID: " + booking.id);
        System.out.println("Movie: " + booking.show.movie.title);
        System.out.println("Total Price: " + booking.totalPrice);
        System.out.println("Tickets: " + booking.tickets.size());
    }
}
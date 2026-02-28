public class TransportBookingService {
    DistanceCalculatorService dist;
    AllocatorService alloc;
    PaymentProcessor pay;

    public TransportBookingService(DistanceCalculatorService dist, AllocatorService alloc, PaymentProcessor pay) {
        this.dist = dist;
        this.alloc = alloc;
        this.pay = pay;
    }

    public void book(TripRequest req) {

        double km = this.dist.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = this.alloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = this.pay.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}

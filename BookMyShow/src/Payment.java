public class Payment {
    String id;
    double amount;
    PaymentStatus status;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.SUCCESS;
    }
}
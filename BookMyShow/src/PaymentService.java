public class PaymentService {
    public boolean processPayment(String userId, double amount) {
        System.out.println("Payment successful: " + amount);
        return true;
    }

    public void refund(String paymentId) {
        System.out.println("Refund processed for " + paymentId);
    }
}
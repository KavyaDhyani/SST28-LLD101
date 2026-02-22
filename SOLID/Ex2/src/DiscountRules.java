public class DiscountRules implements Discount {
    @Override
    public double calculateDiscount(String customerType, double subtotal, int length) {
        if ("student".equalsIgnoreCase(customerType)) {
            return (subtotal >= 180.0) ? 10.0 : 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            return (length >= 3) ? 15.0 : 5.0;
        }
        return 0.0;
    }
}
import java.util.*;
public class PriceCalculator {
    private final Taxation taxation;
    private final Discount discount;

    public PriceCalculator(Taxation taxation, Discount discount) {
        this.taxation = taxation;
        this.discount = discount;
    }

    public Invoice compute(String invId, List<OrderLine> lines, Map<String, MenuItem> menu, String customerType) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            subtotal += menu.get(l.itemId).price * l.qty;
        }
        double taxPct = taxation.calculateTaxPercent(customerType);
        double taxAmount = subtotal * (taxPct / 100.0);
        double discAmount = discount.calculateDiscount(customerType, subtotal, lines.size());
        return new Invoice(invId, lines, subtotal, taxAmount, taxPct, discAmount, subtotal + taxAmount - discAmount);
    }
}
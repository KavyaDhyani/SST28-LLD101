import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        TaxRules taxation = new TaxRules();
        DiscountRules discount = new DiscountRules();
        PriceCalculator calculator = new PriceCalculator(taxation, discount);
        InvoiceFormatter formatter = new InvoiceFormatter();
        FileStore repo = new FileStore();

        CafeteriaSystem sys = new CafeteriaSystem(calculator, formatter, repo);

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout("student", order);
    }
}
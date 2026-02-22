import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PriceCalculator calculator;
    private final InvoiceFormatter formatter;
    private final InvoiceRepository repo;
    private int seq = 1000;

    public CafeteriaSystem(PriceCalculator calculator, InvoiceFormatter formatter, InvoiceRepository r) {
        this.calculator = calculator;
        this.formatter = formatter;
        this.repo = r;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String id = "INV-" + (++seq);
        Invoice inv = this.calculator.compute(id, lines, menu, customerType);
        String text = this.formatter.format(inv, menu);
        System.out.print(text);
        repo.save(id, text);
        System.out.println("Saved invoice: " + id + " (lines=" + text.split("\n").length + ")");
    }
}

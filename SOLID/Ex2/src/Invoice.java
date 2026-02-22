import java.util.List;

public class Invoice {
    String invoiceId;
    List<OrderLine> lines;
    double subtotal;
    double tax;
    double taxPercent;
    double discount;
    double total;

    public Invoice(String invoiceId, List<OrderLine> lines, double subtotal, double tax, double taxPct, double discount, double total) {
        this.invoiceId = invoiceId;
        this.lines = lines;
        this.subtotal = subtotal;
        this.tax = tax;
        this.taxPercent = taxPct;
        this.discount = discount;
        this.total = total;
    }
}

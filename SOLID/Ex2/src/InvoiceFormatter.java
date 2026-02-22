import java.util.Map;
public class InvoiceFormatter {
    public String format(Invoice inv, Map<String, MenuItem> menu) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(inv.invoiceId).append("\n");
        for (OrderLine l : inv.lines) {
            MenuItem item = menu.get(l.itemId);
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, item.price * l.qty));
        }
        sb.append(String.format("Subtotal: %.2f\nTax(%.0f%%): %.2f\nDiscount: -%.2f\nTOTAL: %.2f\n",
                inv.subtotal, inv.taxPercent, inv.tax, inv.discount, inv.total));
        return sb.toString();
    }
}
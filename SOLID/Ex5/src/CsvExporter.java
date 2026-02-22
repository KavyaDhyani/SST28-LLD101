import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    public CsvExporter() {
        super("text/csv", Integer.MAX_VALUE); // CSV has no length limit
    }

    @Override
    protected byte[] encode(ExportRequest req) {
        // Data Integrity: Wrap in quotes to preserve commas/newlines
        String title = "\"" + (req.title == null ? "" : req.title.replace("\"", "\"\"")) + "\"";
        String body = "\"" + (req.body == null ? "" : req.body.replace("\"", "\"\"")) + "\"";

        String csv = "title,body\n" + title + "," + body + "\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected String getFormatName() {
        return "CSV";
    }
}
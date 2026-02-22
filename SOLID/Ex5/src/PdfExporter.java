import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    public PdfExporter() {
        super("application/pdf", 20); // PDF has a strict 20-char contract
    }

    @Override
    protected byte[] encode(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected String getFormatName() {
        return "PDF";
    }
}
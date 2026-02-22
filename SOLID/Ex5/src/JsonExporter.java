import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    public JsonExporter() {
        super("application/json", Integer.MAX_VALUE); // JSON has no length limit
    }

    @Override
    protected byte[] encode(ExportRequest req) {
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return json.getBytes(StandardCharsets.UTF_8);
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }

    @Override
    protected String getFormatName() {
        return "JSON";
    }
}
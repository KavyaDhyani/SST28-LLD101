import java.util.Objects;

public abstract class Exporter {
    private final String contentType;
    private final int maxContentLength;

    protected Exporter(String contentType, int maxContentLength) {
        this.contentType = contentType;
        this.maxContentLength = maxContentLength;
    }

    public final ExportResult export(ExportRequest req) {
        // Enforce Preconditions
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        String body = (req.body == null) ? "" : req.body;

        // Enforce Invariants/Constraints
        if (body.length() > maxContentLength) {
            throw new IllegalArgumentException(getFormatName() +" cannot handle content > " + maxContentLength  +" chars");
        }

        // Delegate to subclasses
        byte[] encodedData = encode(req);

        // Enforce Postconditions
        return new ExportResult(contentType, Objects.requireNonNull(encodedData));
    }

    // This is where subclasses provide their specific logic
    protected abstract byte[] encode(ExportRequest req);

    protected abstract String getFormatName();
}
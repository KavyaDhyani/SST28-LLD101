public class PenFactory {

    public static Pen createPen(String type, String color, String mechanism) {

        WriteStrategy writeStrategy;
        RefillStrategy refillStrategy;

        // Type selection
        switch (type.toUpperCase()) {
            case "BALLPOINT":
                writeStrategy = new BallpointWrite();
                refillStrategy = new StandardRefill();
                break;

            case "GEL":
                writeStrategy = new GelWrite();
                refillStrategy = new StandardRefill();
                break;

            case "INK":
                writeStrategy = new InkWrite();
                refillStrategy = new InkRefill();
                break;

            default:
                throw new IllegalArgumentException("Invalid pen type");
        }

        StartStrategy startStrategy;
        CloseStrategy closeStrategy;

        // Mechanism selection
        switch (mechanism.toUpperCase()) {
            case "CAP":
                startStrategy = new CapStart();
                closeStrategy = new CapClose();
                break;

            case "CLICK":
                startStrategy = new ClickStart();
                closeStrategy = new ClickClose();
                break;

            default:
                throw new IllegalArgumentException("Invalid mechanism");
        }

        return new Pen(
                writeStrategy,
                refillStrategy,
                startStrategy,
                closeStrategy,
                new Ink(color)
        );
    }
}
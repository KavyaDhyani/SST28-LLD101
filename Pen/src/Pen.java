public class Pen {

    private WriteStrategy writeStrategy;
    private RefillStrategy refillStrategy;
    private StartStrategy startStrategy;
    private CloseStrategy closeStrategy;

    private Ink ink;
    private boolean isStarted;

    public Pen(WriteStrategy writeStrategy,
               RefillStrategy refillStrategy,
               StartStrategy startStrategy,
               CloseStrategy closeStrategy,
               Ink ink) {
        this.writeStrategy = writeStrategy;
        this.refillStrategy = refillStrategy;
        this.startStrategy = startStrategy;
        this.closeStrategy = closeStrategy;
        this.ink = ink;
        this.isStarted = false;
    }

    public void start() {
        startStrategy.start();
        isStarted = true;
    }

    public void write(String text) {
        if (!isStarted) {
            System.out.println("Pen is not started!");
            return;
        }
        writeStrategy.write(text, ink);
    }

    public void refill(String newColor) {
        refillStrategy.refill(ink, newColor);
    }

    public void close() {
        closeStrategy.close();
        isStarted = false;
    }
}
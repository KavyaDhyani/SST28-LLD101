public class CapClose implements CloseStrategy {
    public void close() {
        System.out.println("Cap closed");
    }
}
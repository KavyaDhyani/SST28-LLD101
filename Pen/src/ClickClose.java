public class ClickClose implements CloseStrategy {
    public void close() {
        System.out.println("Clicked to close");
    }
}
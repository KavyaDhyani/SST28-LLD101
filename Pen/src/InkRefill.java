public class InkRefill implements RefillStrategy {
    public void refill(Ink ink, String newColor) {
        ink.setColor(newColor);
        System.out.println("Ink bottle refill with: " + newColor);
    }
}
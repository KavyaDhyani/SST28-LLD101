public class StandardRefill implements RefillStrategy {
    public void refill(Ink ink, String newColor) {
        ink.setColor(newColor);
        System.out.println("Refilled with color: " + newColor);
    }
}
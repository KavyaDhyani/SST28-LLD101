public class InkWrite implements WriteStrategy {
    public void write(String text, Ink ink) {
        System.out.println("Ink pen writing elegantly in " + ink.getColor() + ": " + text);
    }
}
public class GelWrite implements WriteStrategy {
    public void write(String text, Ink ink) {
        System.out.println("Gel pen writing boldly in " + ink.getColor() + ": " + text);
    }
}
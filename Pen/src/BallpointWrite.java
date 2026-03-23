public class BallpointWrite implements WriteStrategy {
    public void write(String text, Ink ink) {
        System.out.println("Ballpoint writing smoothly in " + ink.getColor() + ": " + text);
    }
}
public class Main {
    public static void main(String[] args) {

        Pen pen = PenFactory.createPen("GEL", "Blue", "CLICK");

        pen.start();
        pen.write("Hello World!");
        pen.refill("Black");
        pen.write("After refill");
        pen.close();

        pen.write("This will not write");
    }
}
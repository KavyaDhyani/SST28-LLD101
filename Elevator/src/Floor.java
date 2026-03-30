public class Floor {
    int floorNumber;
    boolean upButtonPressed;
    boolean downButtonPressed;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void pressUp() {
        upButtonPressed = true;
    }

    public void pressDown() {
        downButtonPressed = true;
    }
}
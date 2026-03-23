import java.util.List;

public class ParkingLot {
    public List<Level> levels;
    public List<Gate> gates;

    public ParkingLot(List<Level> levels, List<Gate> gates) {
        this.levels = levels;
        this.gates = gates;
    }
}
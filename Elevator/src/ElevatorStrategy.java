import java.util.List;

public interface ElevatorStrategy {
    Elevator selectElevator(List<Elevator> elevators, int floor, Direction direction);
}
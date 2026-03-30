import java.util.List;

public class ElevatorService {

    private List<Elevator> elevators;
    private ElevatorStrategy elevatorStrategy;
    private MovementStrategy movementStrategy;

    public ElevatorService(List<Elevator> elevators,
                           ElevatorStrategy es,
                           MovementStrategy ms) {
        this.elevators = elevators;
        this.elevatorStrategy = es;
        this.movementStrategy = ms;
    }

    public void requestElevator(int floor, Direction direction) {
        Elevator e = elevatorStrategy.selectElevator(elevators, floor, direction);
        if (e != null) {
            System.out.println("Assigning Elevator " + e.id + " to floor " + floor);
            e.addRequest(floor);
        }
    }

    public void pressInternalButton(int elevatorId, int floor) {
        for (Elevator e : elevators) {
            if (e.id == elevatorId) {
                e.addRequest(floor);
            }
        }
    }

    public void step() {
        for (Elevator e : elevators) {

            // Skip maintenance
            if (e.state == State.MAINTENANCE) {
                continue;
            }

            Integer next = movementStrategy.getNextStop(e);

            if (next != null) {

                // Emergency handling
                if (movementStrategy instanceof EmergencyMovementStrategy) {
                    System.out.println("Elevator " + e.id + " stopping at floor " + e.currentFloor);
                    e.requests.clear();
                    e.openDoor();
                    continue;
                }

                e.moveTo(next);
                e.openDoor();
                e.requests.remove(next);
            }
        }
    }
    public void setMovementStrategy(MovementStrategy ms) {
        this.movementStrategy = ms;
    }
}
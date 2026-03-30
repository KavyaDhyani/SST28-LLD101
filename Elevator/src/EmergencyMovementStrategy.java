public class EmergencyMovementStrategy implements MovementStrategy {

    @Override
    public Integer getNextStop(Elevator e) {
        return e.currentFloor;
    }
}
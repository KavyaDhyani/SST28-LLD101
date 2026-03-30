import java.util.List;

public class NearestElevatorStrategy implements ElevatorStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, int floor, Direction direction) {
        Elevator best = null;
        int minDist = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.state == State.MAINTENANCE) continue;

            int dist = Math.abs(e.currentFloor - floor);
            if (dist < minDist) {
                minDist = dist;
                best = e;
            }
        }
        return best;
    }
}
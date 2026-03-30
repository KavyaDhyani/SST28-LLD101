import java.util.TreeSet;

public class SCANMovementStrategy implements MovementStrategy {

    @Override
    public Integer getNextStop(Elevator e) {
        if (e.requests.isEmpty()) return null;

        if (e.direction == Direction.UP || e.direction == Direction.NONE) {
            for (int f : e.requests) {
                if (f >= e.currentFloor) {
                    e.direction = Direction.UP;
                    return f;
                }
            }
            e.direction = Direction.DOWN;
        }

        for (int f : ((TreeSet<Integer>) e.requests).descendingSet()) {
            if (f <= e.currentFloor) {
                return f;
            }
        }
        return null;
    }
}
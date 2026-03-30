import java.util.*;

public class Elevator implements Subject {
    int id;
    int currentFloor = 0;
    int maxWeight = 700;
    int currentWeight = 0;

    State state = State.IDLE;
    Direction direction = Direction.NONE;

    Set<Integer> requests = new TreeSet<>();
    private List<Observer> observers = new ArrayList<>();

    public Elevator(int id) {
        this.id = id;
    }

    public void addRequest(int floor) {
        if (state == State.MAINTENANCE) return;
        requests.add(floor);
    }

    public void moveTo(int floor) {
        System.out.println("Elevator " + id + " moving to floor " + floor);
        currentFloor = floor;
        notifyObservers(new Event(EventType.FLOOR_REACHED, id, floor));
    }

    public void openDoor() {
        System.out.println("Elevator " + id + " door opened");
        notifyObservers(new Event(EventType.DOOR_OPEN, id, currentFloor));
    }

    public void setMaintenance() {
        System.out.println("Elevator " + id + " under maintenance");
        this.state = State.MAINTENANCE;
        this.requests.clear();
    }

    public boolean isOverloaded() {
        return currentWeight > maxWeight;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Event e) {
        for (Observer o : observers) {
            o.update(e);
        }
    }
}
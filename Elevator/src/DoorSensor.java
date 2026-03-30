public class DoorSensor implements Observer {

    @Override
    public void update(Event e) {
        if (e.type == EventType.DOOR_OPEN) {
            System.out.println("Door opened at floor " + e.value);
        }
    }
}
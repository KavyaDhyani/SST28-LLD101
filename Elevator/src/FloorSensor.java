public class FloorSensor implements Observer {

    @Override
    public void update(Event e) {
        if (e.type == EventType.FLOOR_REACHED) {
            System.out.println("Reached floor " + e.value);
        }
    }
}
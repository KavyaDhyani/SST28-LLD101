public class WeightSensor implements Observer {

    @Override
    public void update(Event e) {
        if (e.type == EventType.WEIGHT_CHANGE) {
            System.out.println("Weight updated: " + e.value);
        }
    }
}
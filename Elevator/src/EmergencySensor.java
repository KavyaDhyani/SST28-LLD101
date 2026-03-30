public class EmergencySensor implements Observer {

    @Override
    public void update(Event e) {
        if (e.type == EventType.EMERGENCY_TRIGGERED) {
            System.out.println("Emergency in elevator " + e.elevatorId);
        }
    }
}
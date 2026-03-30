public class Event {
    EventType type;
    int elevatorId;
    int value;

    public Event(EventType type, int elevatorId, int value) {
        this.type = type;
        this.elevatorId = elevatorId;
        this.value = value;
    }
}
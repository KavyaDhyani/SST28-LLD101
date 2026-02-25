import java.util.*;

public class DeviceRegistry {
    private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    public <T extends SmartClassroomDevice> T getFirstOfType(Class<T> device) {
        for (SmartClassroomDevice d : devices) {
            if (device.isInstance(d)) return device.cast(d);
        }
        throw new IllegalStateException("Missing: " + device.getSimpleName());
    }
}

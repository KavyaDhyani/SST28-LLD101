import java.util.*;

public class Main {

    public static void main(String[] args) {

        Elevator e1 = new Elevator(1);
        Elevator e2 = new Elevator(2);

        // Sensors
        Observer floorSensor = new FloorSensor();
        Observer doorSensor = new DoorSensor();

        e1.registerObserver(floorSensor);
        e1.registerObserver(doorSensor);

        e2.registerObserver(floorSensor);
        e2.registerObserver(doorSensor);

        List<Elevator> elevators = Arrays.asList(e1, e2);

        ElevatorService service = new ElevatorService(
                elevators,
                new NearestElevatorStrategy(),
                new SCANMovementStrategy()
        );

        // ================= NORMAL FLOW =================
        service.requestElevator(5, Direction.UP);
        service.pressInternalButton(1, 8);
        service.pressInternalButton(1, 2);

        System.out.println("\n--- Normal Simulation ---");
        for (int i = 0; i < 3; i++) {
            service.step();
        }

        // ================= MAINTENANCE =================
        System.out.println("\n--- Maintenance Mode ---");
        e2.setMaintenance();

        service.requestElevator(3, Direction.DOWN); // should NOT assign e2

        for (int i = 0; i < 2; i++) {
            service.step();
        }

        // ================= EMERGENCY =================
        System.out.println("\n--- Emergency Mode ---");

        service.setMovementStrategy(new EmergencyMovementStrategy());

        service.step(); // emergency action
    }
}
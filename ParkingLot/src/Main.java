import java.util.*;

public class Main {

    public static void main(String[] args) {


        // 1. Create Gate

        Gate gate1 = new Gate(1);


        // 2. Create Slots

        List<Slot> slots = Arrays.asList(
                new Slot(1, SlotType.SMALL),
                new Slot(2, SlotType.SMALL),
                new Slot(3, SlotType.MEDIUM),
                new Slot(4, SlotType.MEDIUM),
                new Slot(5, SlotType.LARGE)
        );


        // 3. Create Level & ParkingLot

        Level level1 = new Level(1, slots);

        ParkingLot parkingLot = new ParkingLot(
                Arrays.asList(level1),
                Arrays.asList(gate1)
        );


        // 4. Initialize Allocator

        NearestSlotAllocator allocator = new NearestSlotAllocator(parkingLot.gates);

        // preload all slots into PQ
        for (Slot slot : slots) {
            allocator.addSlot(gate1, slot);
        }


        // 5. Pricing Strategy

        PricingStrategy pricingStrategy = new HourlyPricingStrategy();


        // 6. Parking Service

        ParkingService service = new ParkingService(
                parkingLot,
                allocator,
                pricingStrategy
        );


        // 7. Display Initial Availability

        System.out.println("=== Initial Availability ===");
        service.displayAvailability();


        // 8. Park Vehicles

        VehicleDetails v1 = new VehicleDetails("KA01AB1234", VehicleType.CAR);
        VehicleDetails v2 = new VehicleDetails("KA02XY9999", VehicleType.BIKE);

        Ticket t1 = service.park(v1, gate1, SlotType.MEDIUM);
        Ticket t2 = service.park(v2, gate1, SlotType.SMALL);

        System.out.println("\n=== After Parking ===");
        service.displayAvailability();


        // 9. Simulate Time Delay

        try {
            Thread.sleep(2000); // 2 sec (just for demo)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 10. Exit Vehicles

        double fee1 = service.exit(t1);
        double fee2 = service.exit(t2);

        System.out.println("\n=== After Exit ===");
        service.displayAvailability();


        // 11. Print Fees

        System.out.println("\nFees:");
        System.out.println("Vehicle 1: " + fee1);
        System.out.println("Vehicle 2: " + fee2);
    }
}
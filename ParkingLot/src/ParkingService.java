import java.util.UUID;

public class ParkingService {

    public ParkingLot parkingLot;
    public SlotAllocator slotAllocator;
    public PricingStrategy pricingStrategy;

    public ParkingService(ParkingLot parkingLot,
                          SlotAllocator slotAllocator,
                          PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.slotAllocator = slotAllocator;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket park(VehicleDetails vehicle, Gate gate, SlotType type) {

        Slot slot = slotAllocator.getSlot(gate, type);
        if (slot == null) return null;

        slot.isAvailable = false;
        slot.currentVehicle = vehicle;

        Level level = findLevel(slot);
        updateCount(level, type, -1);

        return new Ticket(UUID.randomUUID().toString(), slot, gate, vehicle);
    }

    public double exit(Ticket ticket) {

        Slot slot = ticket.slot;

        slot.isAvailable = true;
        slot.currentVehicle = null;

        if (slotAllocator instanceof NearestSlotAllocator) {
            ((NearestSlotAllocator) slotAllocator).addSlot(ticket.gate, slot);
        }

        Level level = findLevel(slot);
        updateCount(level, slot.slotType, +1);

        return pricingStrategy.calculatePrice(
                ticket.entryTime,
                System.currentTimeMillis(),
                slot.slotType
        );
    }

    public void displayAvailability() {
        for (Level level : parkingLot.levels) {
            System.out.println("Level " + level.id);
            System.out.println("SMALL: " + level.availableSmallCount);
            System.out.println("MEDIUM: " + level.availableMediumCount);
            System.out.println("LARGE: " + level.availableLargeCount);
        }
    }

    private Level findLevel(Slot slot) {
        for (Level level : parkingLot.levels) {
            if (level.slots.contains(slot)) return level;
        }
        return null;
    }

    private void updateCount(Level level, SlotType type, int delta) {
        if (type == SlotType.SMALL) level.availableSmallCount += delta;
        else if (type == SlotType.MEDIUM) level.availableMediumCount += delta;
        else level.availableLargeCount += delta;
    }
}
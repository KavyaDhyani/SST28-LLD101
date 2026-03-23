public class Slot {
    public int id;
    public SlotType slotType;
    public boolean isAvailable;
    public VehicleDetails currentVehicle;

    public Slot(int id, SlotType slotType) {
        this.id = id;
        this.slotType = slotType;
        this.isAvailable = true;
    }
}
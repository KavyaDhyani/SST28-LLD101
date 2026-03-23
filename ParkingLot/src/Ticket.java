public class Ticket {
    public String ticketId;
    public long entryTime;

    public Slot slot;
    public Gate gate;
    public VehicleDetails vehicleDetails;

    public Ticket(String ticketId, Slot slot, Gate gate, VehicleDetails vehicleDetails) {
        this.ticketId = ticketId;
        this.slot = slot;
        this.gate = gate;
        this.vehicleDetails = vehicleDetails;
        this.entryTime = System.currentTimeMillis();
    }
}
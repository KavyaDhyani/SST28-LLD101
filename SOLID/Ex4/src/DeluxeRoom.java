public class DeluxeRoom implements Room {
    @Override public int getTypeId() { return LegacyRoomTypes.DELUXE; }
    @Override public double getMonthlyRate() { return 16000.0; }
}
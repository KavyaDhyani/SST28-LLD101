public class SingleRoom implements Room {
    @Override public int getTypeId() { return LegacyRoomTypes.SINGLE; }
    @Override public double getMonthlyRate() { return 14000.0; }
}
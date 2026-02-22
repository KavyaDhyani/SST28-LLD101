public class DoubleRoom implements Room {
    @Override public int getTypeId() { return LegacyRoomTypes.DOUBLE; }
    @Override public double getMonthlyRate() { return 15000.0; }
}
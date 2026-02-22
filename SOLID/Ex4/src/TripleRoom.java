public class TripleRoom implements Room {
    @Override public int getTypeId() { return LegacyRoomTypes.TRIPLE; }
    @Override public double getMonthlyRate() { return 12000.0; }
}
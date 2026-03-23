public interface PricingStrategy {
    double calculatePrice(long entryTime, long exitTime, SlotType slotType);
}
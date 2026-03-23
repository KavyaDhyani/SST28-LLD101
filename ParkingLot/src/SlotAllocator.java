public interface SlotAllocator {
    Slot getSlot(Gate gate, SlotType slotType);
}
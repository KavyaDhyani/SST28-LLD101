import java.util.*;

public class NearestSlotAllocator implements SlotAllocator {

    public Map<Gate, Map<SlotType, PriorityQueue<Slot>>> availableSlotsMap;

    public NearestSlotAllocator(List<Gate> gates) {
        availableSlotsMap = new HashMap<>();

        for (Gate gate : gates) {
            Map<SlotType, PriorityQueue<Slot>> map = new HashMap<>();

            for (SlotType type : SlotType.values()) {
                map.put(type, new PriorityQueue<>(Comparator.comparingInt(s -> s.id)));
            }

            availableSlotsMap.put(gate, map);
        }
    }

    @Override
    public Slot getSlot(Gate gate, SlotType slotType) {
        PriorityQueue<Slot> pq = availableSlotsMap.get(gate).get(slotType);
        return (pq == null || pq.isEmpty()) ? null : pq.poll();
    }

    public void addSlot(Gate gate, Slot slot) {
        availableSlotsMap.get(gate).get(slot.slotType).offer(slot);
    }
}
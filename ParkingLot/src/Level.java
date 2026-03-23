import java.util.List;

public class Level {
    public int id;
    public List<Slot> slots;

    public int availableSmallCount;
    public int availableMediumCount;
    public int availableLargeCount;

    public Level(int id, List<Slot> slots) {
        this.id = id;
        this.slots = slots;

        for (Slot s : slots) {
            if (s.slotType == SlotType.SMALL) availableSmallCount++;
            else if (s.slotType == SlotType.MEDIUM) availableMediumCount++;
            else availableLargeCount++;
        }
    }
}
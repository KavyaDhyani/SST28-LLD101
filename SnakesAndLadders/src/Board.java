import java.util.*;

public class Board {

    private int size;
    private Map<Integer, Jump> jumps;

    public Board(int size) {
        this.size = size;
        this.jumps = new HashMap<>();
    }

    public void addJump(Jump jump) {
        jumps.put(jump.getStart(), jump);
    }

    public int resolvePosition(int position) {

        if (jumps.containsKey(position)) {

            Jump jump = jumps.get(position);

            if (jump.getEnd() > jump.getStart())
                System.out.println("Ladder! Climb up to " + jump.getEnd());
            else
                System.out.println("Snake! Slide down to " + jump.getEnd());

            return jump.getEnd();
        }

        return position;
    }

    public int getSize() {
        return size;
    }
}
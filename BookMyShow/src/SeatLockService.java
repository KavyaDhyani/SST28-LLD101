import java.util.*;

public class SeatLockService {

    private final Map<String, Set<String>> showLocks = new HashMap<>();
    private final Map<String, Object> showMutex = new HashMap<>();

    public boolean lockSeats(String showId, List<String> seatIds) {

        showMutex.putIfAbsent(showId, new Object());

        synchronized (showMutex.get(showId)) {

            showLocks.putIfAbsent(showId, new HashSet<>());
            Set<String> locked = showLocks.get(showId);

            for (String seat : seatIds) {
                if (locked.contains(seat)) {
                    return false;
                }
            }

            locked.addAll(seatIds);
            return true;
        }
    }

    public void unlockSeats(String showId, List<String> seatIds) {

        synchronized (showMutex.get(showId)) {
            if (showLocks.containsKey(showId)) {
                showLocks.get(showId).removeAll(seatIds);
            }
        }
    }
}
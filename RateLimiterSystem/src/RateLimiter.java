import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

class RateLimiter {

    private Map<String, Queue<RequestDTO>> requestMap;
    private int maxReqAllowed;
    private long windowSize;
    private RLStrategy strategy;

    public RateLimiter(int maxReqAllowed, long windowSize, RLStrategy strategy) {
        this.requestMap = new ConcurrentHashMap<>();
        this.maxReqAllowed = maxReqAllowed;
        this.windowSize = windowSize;
        this.strategy = strategy;
    }

    public boolean allow(RequestDTO request) {
        String key = request.getKey();

        // Thread-safe queue per key
        requestMap.putIfAbsent(key, new ConcurrentLinkedQueue<>());
        Queue<RequestDTO> queue = requestMap.get(key);

        return strategy.validateRequest(queue, maxReqAllowed, request, windowSize);
    }
}
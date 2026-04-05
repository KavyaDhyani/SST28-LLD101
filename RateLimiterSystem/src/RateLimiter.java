import java.util.LinkedList;
import java.util.Queue;

class RateLimiter {
    private Queue<RequestDTO> requests;
    private int maxReqAllowed;
    private long windowSize;
    private RLStrategy strategy;

    public RateLimiter(int maxReqAllowed, long windowSize, RLStrategy strategy) {
        this.requests = new LinkedList<>();
        this.maxReqAllowed = maxReqAllowed;
        this.windowSize = windowSize;
        this.strategy = strategy;
    }

    public boolean allow(RequestDTO request) {
        return strategy.validateRequest(requests, maxReqAllowed, request, windowSize);
    }
}
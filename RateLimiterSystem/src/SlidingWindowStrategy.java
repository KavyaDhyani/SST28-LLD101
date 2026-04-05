import java.util.Queue;

class SlidingWindowStrategy implements RLStrategy {

    @Override
    public boolean validateRequest(Queue<RequestDTO> queue, int maxReq, RequestDTO request, long windowSize) {
        long threshold = request.timestamp - windowSize;

        while (!queue.isEmpty() && queue.peek().timestamp <= threshold) {
            queue.poll();
        }

        if (queue.size() < maxReq) {
            queue.offer(request);
            return true;
        }

        return false;
    }
}
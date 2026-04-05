import java.util.Queue;

class FixedWindowStrategy implements RLStrategy {

    @Override
    public boolean validateRequest(Queue<RequestDTO> queue, int maxReq, RequestDTO request, long windowSize) {
        long currentWindow = request.timestamp / windowSize;

        while (!queue.isEmpty()) {
            RequestDTO req = queue.peek();
            long reqWindow = req.timestamp / windowSize;

            if (reqWindow != currentWindow) {
                queue.poll();
            } else {
                break;
            }
        }

        if (queue.size() < maxReq) {
            queue.offer(request);
            return true;
        }

        return false;
    }
}
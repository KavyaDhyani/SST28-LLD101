import java.util.Queue;

interface RLStrategy {
    boolean validateRequest(Queue<RequestDTO> queue, int maxReq, RequestDTO request, long windowSize);
}
import java.util.concurrent.*;

public class RequestCollapser<K, V> {

    private final ConcurrentHashMap<K, CompletableFuture<V>> inFlight = new ConcurrentHashMap<>();

    public CompletableFuture<V> getOrCreate(K key) {
        return inFlight.computeIfAbsent(key, k -> new CompletableFuture<>());
    }

    public void complete(K key, V value) {
        CompletableFuture<V> future = inFlight.get(key);
        if (future != null) {
            future.complete(value);
            inFlight.remove(key);
        }
    }
}
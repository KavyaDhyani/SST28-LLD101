import java.util.*;

public class CacheNode<K, V> {

    private final int capacity;
    private final Map<K, CacheEntry<V>> map;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public synchronized V get(K key) {
        if (!map.containsKey(key)) return null;

        CacheEntry<V> entry = map.get(key);

        if (entry.isExpired()) {
            System.out.println("[Node] Key " + key + " EXPIRED");
            map.remove(key);
            return null;
        }

        evictionPolicy.keyAccessed(key);
        return entry.getValue();
    }

    public synchronized void put(K key, V value, long ttl) {

        if (map.containsKey(key)) {
            map.put(key, new CacheEntry<>(value, ttl));
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (map.size() >= capacity) {
            K evict = evictionPolicy.evictKey();
            map.remove(evict);
        }

        map.put(key, new CacheEntry<>(value, ttl));
        evictionPolicy.keyAccessed(key);
    }

    public Map<K, CacheEntry<V>> getAllEntries() {
        return new HashMap<>(map);
    }

    public void clear() {
        map.clear();
    }
}
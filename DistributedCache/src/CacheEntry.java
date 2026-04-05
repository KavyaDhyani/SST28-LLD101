public class CacheEntry<V> {
    private V value;
    private long expiryTime;

    public CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }

    public V getValue() {
        return value;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DistributedCache<K, V> {

    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K> strategy;
    private final DataSource<K, V> db;
    private final RequestCollapser<K, V> collapser;

    public DistributedCache(int numNodes, int capacityPerNode,
                            DistributionStrategy<K> strategy,
                            DataSource<K, V> db) {
        this.strategy = strategy;
        this.db = db;
        this.collapser = new RequestCollapser<>();
        this.nodes = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            nodes.add(new CacheNode<>(capacityPerNode, new LRUEvictionPolicy<>()));
        }
    }

    public V get(K key) {
        int index = strategy.getNode(key, nodes.size());
        CacheNode<K, V> node = nodes.get(index);

        V value = node.get(key);
        if (value != null) return value;

        CompletableFuture<V> future = collapser.getOrCreate(key);

        synchronized (future) {
            if (!future.isDone()) {
                V dbValue = db.get(key);
                node.put(key, dbValue, 5000);
                collapser.complete(key, dbValue);
            }
        }

        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    public void put(K key, V value, long ttl) {
        int index = strategy.getNode(key, nodes.size());
        nodes.get(index).put(key, value, ttl);
    }

    public void addNode(CacheNode<K, V> node) {
        nodes.add(node);
        rebalance();
    }

    public void removeNode(int index) {
        CacheNode<K, V> removed = nodes.remove(index);

        for (Map.Entry<K, CacheEntry<V>> entry : removed.getAllEntries().entrySet()) {
            put(entry.getKey(), entry.getValue().getValue(),
                    entry.getValue().getExpiryTime());
        }
    }

    private void rebalance() {
        List<Map.Entry<K, CacheEntry<V>>> allEntries = new ArrayList<>();

        for (CacheNode<K, V> node : nodes) {
            allEntries.addAll(node.getAllEntries().entrySet());
            node.clear();
        }

        for (Map.Entry<K, CacheEntry<V>> entry : allEntries) {
            put(entry.getKey(), entry.getValue().getValue(),
                    entry.getValue().getExpiryTime());
        }
    }
}
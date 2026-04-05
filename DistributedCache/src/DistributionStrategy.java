public interface DistributionStrategy<K> {
    int getNode(K key, int totalNodes);
}
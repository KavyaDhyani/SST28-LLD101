public class ModuloDistribution<K> implements DistributionStrategy<K> {

    @Override
    public int getNode(K key, int totalNodes) {
        return Math.abs(key.hashCode()) % totalNodes;
    }
}
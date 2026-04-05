public class Database<K, V> implements DataSource<K, V>{

    public V get(K key) {
        System.out.println("DB HIT for key: " + key);
        try { Thread.sleep(100); } catch (Exception ignored) {}
        System.out.println("DB Fetching fresh value for key: " + key);
        return (V) ("Value_" + key);
    }
}
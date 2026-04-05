import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private class Node {
        K key;
        Node prev, next;

        Node(K key) {
            this.key = key;
        }
    }

    private final Map<K, Node> map = new HashMap<>();
    private final Node head; // dummy head
    private final Node tail; // dummy tail

    public LRUEvictionPolicy() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    // =========================
    // Core Operations
    // =========================

    @Override
    public void keyAccessed(K key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            addToTail(node);
        } else {
            Node node = new Node(key);
            map.put(key, node);
            addToTail(node);
        }
    }

    @Override
    public K evictKey() {
        Node lru = head.next; // least recently used
        remove(lru);
        map.remove(lru.key);
        return lru.key;
    }

    // =========================
    // DLL Helpers
    // =========================

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToTail(Node node) {
        Node prev = tail.prev;

        prev.next = node;
        node.prev = prev;

        node.next = tail;
        tail.prev = node;
    }
}
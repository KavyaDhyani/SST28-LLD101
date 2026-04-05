public class Main {

    public static void main(String[] args) {

        System.out.println("DISTRIBUTED CACHE DEMO");

        DistributedCache<String, String> cache =
                new DistributedCache<>(2, 2,
                        new ModuloDistribution<>(),
                        new Database<>());

        // ---------------- PUT ----------------
        System.out.println("\n--- STEP 1: PUT VALUES ---");
        cache.put("A", "Apple", 3000);
        System.out.println("Inserted (A, Apple)");

        cache.put("B", "Ball", 3000);
        System.out.println("Inserted (B, Ball)");

        // ---------------- CACHE HIT ----------------
        System.out.println("\n--- STEP 2: CACHE HIT ---");
        System.out.println("Fetching A (should be cache hit)");
        System.out.println("Result: " + cache.get("A"));

        // ---------------- CACHE MISS ----------------
        System.out.println("\n--- STEP 3: CACHE MISS ---");
        System.out.println("Fetching C (not present → should hit DB)");
        System.out.println("Result: " + cache.get("C"));

        // ---------------- REQUEST COLLAPSING ----------------
        System.out.println("\n--- STEP 4: REQUEST COLLAPSING ---");
        System.out.println("Two threads requesting key D simultaneously");

        Runnable task = () -> {
            String value = cache.get("D");
            System.out.println(Thread.currentThread().getName() +
                    " received value: " + value);
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try { Thread.sleep(1000); } catch (Exception ignored) {}

        System.out.println("If working correctly, DB should be hit ONLY ONCE\n");

        // ---------------- TTL EXPIRY ----------------
        System.out.println("\n--- STEP 5: TTL EXPIRY ---");
        System.out.println("Waiting for 4 seconds (TTL was 3 sec)...");

        try { Thread.sleep(4000); } catch (Exception ignored) {}

        System.out.println("Fetching A again (should be expired → DB hit)");
        System.out.println("Result: " + cache.get("A"));

        // ---------------- SCALING UP ----------------
        System.out.println("\n--- STEP 6: SCALING UP ---");
        System.out.println("Adding a new cache node...");

        cache.addNode(new CacheNode<>(2, new LRUEvictionPolicy<>()));

        System.out.println("Fetching B after scaling (may be redistributed)");
        System.out.println("Result: " + cache.get("B"));

        // ---------------- SCALING DOWN ----------------
        System.out.println("\n--- STEP 7: SCALING DOWN ---");
        System.out.println("Removing node 0...");

        cache.removeNode(0);

        System.out.println("Fetching A after node removal");
        System.out.println("Result: " + cache.get("A"));

        System.out.println("DEMO COMPLETE");
    }
}
public class Main {

    public static void main(String[] args) throws InterruptedException {

        int maxRequests = 3;
        long windowSize = 5000; // 5 seconds

        // Choose strategy
        RLStrategy strategy = RLFactory.getStrategy("SLIDING");

        // Build system
        RateLimiter rateLimiter = new RateLimiter(maxRequests, windowSize, strategy);
        RLService rlService = new RLService(rateLimiter);
        RemoteResource resource = new RemoteResourceWrapper(rlService);

        String user = "user1";

        // First burst of requests
        for (int i = 1; i <= 5; i++) {
            RequestDTO request = new RequestDTO(
                    user,
                    "apiKey",
                    "127.0.0.1",
                    System.currentTimeMillis()
            );

            System.out.println("Request " + i + ": " + resource.callRemote(request));
            Thread.sleep(1000);
        }

        // Wait for reset
        System.out.println("\n⏳ Waiting for window reset...\n");
        Thread.sleep(6000);

        // Second burst
        for (int i = 6; i <= 10; i++) {
            RequestDTO request = new RequestDTO(
                    user,
                    "apiKey",
                    "127.0.0.1",
                    System.currentTimeMillis()
            );

            System.out.println("Request " + i + ": " + resource.callRemote(request));
            Thread.sleep(1000);
        }
    }
}
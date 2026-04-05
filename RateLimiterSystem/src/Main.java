public class Main {

    public static void main(String[] args) throws InterruptedException {

        int maxRequests = 3;
        long windowSize = 5000; // 5 seconds

        RLStrategy strategy = RLFactory.getStrategy("SLIDING");

        RateLimiter rateLimiter = new RateLimiter(maxRequests, windowSize, strategy);
        RLService rlService = new RLService(rateLimiter);
        RemoteResource resource = new RemoteResourceWrapper(rlService);

        // Simulating multiple users
        String[] users = {"user1", "user2"};

        for (int i = 1; i <= 6; i++) {
            for (String user : users) {
                RequestDTO request = new RequestDTO(
                        user,
                        "apiKey",
                        "127.0.0.1",
                        System.currentTimeMillis()
                );

                System.out.println("User: " + user + " Request " + i + ": " +
                        resource.callRemote(request));
            }

            Thread.sleep(1000);
        }
    }
}
class RequestDTO {
    String userId;
    String apiKey;
    String ip;
    long timestamp;

    public RequestDTO(String userId, String apiKey, String ip, long timestamp) {
        this.userId = userId;
        this.apiKey = apiKey;
        this.ip = ip;
        this.timestamp = timestamp;
    }

    // Decide rate limit key
    public String getKey() {
        return userId; // can switch to apiKey/ip/etc.
    }
}
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
}
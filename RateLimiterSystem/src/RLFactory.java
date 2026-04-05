class RLFactory {

    public static RLStrategy getStrategy(String type) {
        if (type.equalsIgnoreCase("FIXED")) {
            return new FixedWindowStrategy();
        } else if (type.equalsIgnoreCase("SLIDING")) {
            return new SlidingWindowStrategy();
        } else {
            throw new IllegalArgumentException("Invalid strategy type");
        }
    }
}
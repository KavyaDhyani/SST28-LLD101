public class BasePricingStrategy implements PricingStrategy {
    public double calculatePrice(Show show, Seat seat) {
        switch (seat.type) {
            case GOLD: return 200;
            case PLATINUM: return 300;
            default: return 100;
        }
    }
}
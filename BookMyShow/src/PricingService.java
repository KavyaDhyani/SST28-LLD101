import java.util.*;

public class PricingService {
    List<PricingStrategy> strategies = new ArrayList<>();

    public PricingService() {
        strategies.add(new BasePricingStrategy());
    }

    public double getPrice(Show show, Seat seat) {
        double total = 0;
        for (PricingStrategy s : strategies) {
            total += s.calculatePrice(show, seat);
        }
        return total;
    }
}
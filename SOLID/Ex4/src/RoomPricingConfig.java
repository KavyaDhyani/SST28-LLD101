import java.util.HashMap;
import java.util.Map;

public class RoomPricingConfig implements RoomPricing{
    private final HashMap<Integer, Double> prices;
    private final double defaultPrice;
    public RoomPricingConfig(HashMap<Integer, Double> prices, double defaultPrice) {
        this.prices = prices;
        this.defaultPrice = defaultPrice;
    }

    public double getPrice(int roomType) {
        return prices.getOrDefault(roomType, defaultPrice);
    }
}

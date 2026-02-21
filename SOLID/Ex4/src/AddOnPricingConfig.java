import java.util.HashMap;

public class AddOnPricingConfig implements AddOnPricing{
    private final HashMap<AddOn, Double> prices;
    public AddOnPricingConfig(HashMap<AddOn, Double> prices) {
        this.prices = prices;
    }

    public double getPrice(AddOn addOn) {
        return prices.getOrDefault(addOn, 0.0);
    }
}

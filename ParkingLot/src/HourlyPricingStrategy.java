import java.util.*;

public class HourlyPricingStrategy implements PricingStrategy {

    public Map<SlotType, Double> hourlyRates;

    public HourlyPricingStrategy() {
        hourlyRates = new HashMap<>();
        hourlyRates.put(SlotType.SMALL, 10.0);
        hourlyRates.put(SlotType.MEDIUM, 20.0);
        hourlyRates.put(SlotType.LARGE, 30.0);
    }

    @Override
    public double calculatePrice(long entryTime, long exitTime, SlotType slotType) {
        long hours = ((exitTime - entryTime) / (1000 * 60 * 60)) + 1;
        return hours * hourlyRates.get(slotType);
    }
}
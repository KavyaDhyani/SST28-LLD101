import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        HashMap<Integer, Double> roomPrices = new HashMap<>(Map.of(
                LegacyRoomTypes.SINGLE, 14000.0,
                LegacyRoomTypes.DOUBLE, 15000.0,
                LegacyRoomTypes.TRIPLE, 12000.0

        ));

        HashMap<AddOn, Double> addOnPrices = new HashMap<>(Map.of(
                AddOn.MESS, 1000.0,
                AddOn.LAUNDRY, 500.0,
                AddOn.GYM, 300.0
        ));

        RoomPricing roomPricing = new RoomPricingConfig(roomPrices, 16000.0);
        AddOnPricing addOnPricing = new AddOnPricingConfig(addOnPrices);
        BookingRepository repo = new FakeBookingRepo();

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(repo, roomPricing, addOnPricing);
        calc.process(req);
    }
}

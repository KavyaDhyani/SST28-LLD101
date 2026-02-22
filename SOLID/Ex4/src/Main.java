import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        List<Room> rooms = List.of(new SingleRoom(), new DoubleRoom(), new TripleRoom(), new DeluxeRoom());
        // The list of all supported add-on services
        List<AddOnService> addOnServices = List.of(new MessService(), new LaundryService(), new GymService());

        BookingRepository repo = new FakeBookingRepo();

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(repo, rooms, addOnServices, 5000.0);
        calc.process(req);
    }
}

import java.util.*;

public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final List<Room> rooms;
    private final List<AddOnService> addOnServices;
    private final double deposit;

    public HostelFeeCalculator(BookingRepository repo, List<Room> rooms, List<AddOnService> addOnServices, double deposit
    ) {
        this.repo = repo;
        this.rooms = rooms;
        this.addOnServices = addOnServices;
        this.deposit = deposit;

    }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        // Find Room Price
        double monthlyBase = 0.0;
        for (Room r : rooms) {
            if (r.getTypeId() == req.roomType) {
                monthlyBase = r.getMonthlyRate();
                break;
            }
        }
        // Find Add-On Prices
        double addOnTotal = 0.0;
        for (AddOn a : req.addOns) {
            for (AddOnService service : addOnServices) {
                if (service.getType() == a) {
                    addOnTotal += service.getPrice();
                }
            }
        }
        Money monthly = new Money(monthlyBase + addOnTotal);
        Money deposit = new Money(this.deposit);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }
}

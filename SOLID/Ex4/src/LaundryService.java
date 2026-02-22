public class LaundryService implements AddOnService{
    @Override public AddOn getType() { return AddOn.LAUNDRY; }
    @Override public double getPrice() { return 500.0; }
}

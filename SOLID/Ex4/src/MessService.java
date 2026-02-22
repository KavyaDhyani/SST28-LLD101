public class MessService implements AddOnService{
    @Override public AddOn getType() { return AddOn.MESS; }
    @Override public double getPrice() { return 1000.0; }
}

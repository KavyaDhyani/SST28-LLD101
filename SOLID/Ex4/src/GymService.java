public class GymService implements AddOnService{
    @Override public AddOn getType() { return AddOn.GYM; }
    @Override public double getPrice() { return 300.0; }
}

public class EasyValidator extends Validator {

    @Override
    public boolean canRollAgain(int diceValue, int consecutiveSixCount) {
        return diceValue == 6;
    }

}
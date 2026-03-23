public class HardValidator extends Validator {

    @Override
    public boolean canRollAgain(int diceValue, int consecutiveSixCount) {

        if (diceValue != 6)
            return false;

        return consecutiveSixCount < 3;
    }

}
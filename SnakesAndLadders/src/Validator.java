public abstract class Validator {

    public boolean validateMove(int currentPosition, int diceValue, int boardSize) {
        return currentPosition + diceValue <= boardSize;
    }

    public boolean hasWon(int position, int boardSize) {
        return position == boardSize;
    }

    public abstract boolean canRollAgain(int diceValue, int consecutiveSixCount);
}
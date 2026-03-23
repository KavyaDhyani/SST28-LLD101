import java.util.*;

public class Game {

    private Board board;
    private Dice dice;
    private Validator validator;
    private Queue<Player> players;

    public Game(Board board, Dice dice, Validator validator, Queue<Player> players) {
        this.board = board;
        this.dice = dice;
        this.validator = validator;
        this.players = players;
    }

    public void startGame() {

        while (true) {

            Player player = nextPlayer();
            playTurn(player);

            if (validator.hasWon(player.getCurrentPosition(), board.getSize())) {
                System.out.println(player.getName() + " wins the game!");
                break;
            }

            players.offer(player);
        }
    }

    private Player nextPlayer() {
        return players.poll();
    }

    private void playTurn(Player player) {

        int consecutiveSixCount = 0;
        boolean rollAgain = true;

        while (rollAgain) {

            int diceValue = dice.roll();

            System.out.println(player.getName() + " rolled " + diceValue);

            if (!validator.validateMove(player.getCurrentPosition(), diceValue, board.getSize())) {
                System.out.println("Move exceeds board size. Turn skipped.");
                return;
            }

            int newPosition = player.getCurrentPosition() + diceValue;

            newPosition = board.resolvePosition(newPosition);

            player.setCurrentPosition(newPosition);

            System.out.println(player.getName() + " moved to " + newPosition);

            if (validator.hasWon(newPosition, board.getSize()))
                return;

            if (diceValue == 6)
                consecutiveSixCount++;
            else
                consecutiveSixCount = 0;

            rollAgain = validator.canRollAgain(diceValue, consecutiveSixCount);

            if (!rollAgain)
                System.out.println("Turn over.");
        }
    }
}
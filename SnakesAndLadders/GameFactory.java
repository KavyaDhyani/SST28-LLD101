import java.util.*;

public class GameFactory {

    public static Game createGame(int size, String difficulty, Queue<Player> players) {

        Board board = new Board(100);

        board.addJump(new Jump(4, 14));
        board.addJump(new Jump(9, 31));
        board.addJump(new Jump(17, 7));
        board.addJump(new Jump(54, 34));
        board.addJump(new Jump(62, 19));
        board.addJump(new Jump(64, 60));
        board.addJump(new Jump(87, 24));
        board.addJump(new Jump(93, 73));

        Dice dice = new Dice();

        if(difficulty.equalsIgnoreCase("hard")){
            return new Game(board, dice, new HardValidator(), players);
        }

        return new Game(board, dice, new EasyValidator(), players);

    }
}
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter n: ");
        int size = scan.nextInt();

        List<String> names = Arrays.asList("Alice", "Bob");

        Queue<Player> players = PlayerFactory.createPlayers(names);

        Game game = GameFactory.createGame(size, "hard", players);

        game.startGame();
    }
}
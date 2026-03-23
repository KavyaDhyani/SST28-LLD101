import java.util.*;

public class PlayerFactory {

    public static Queue<Player> createPlayers(List<String> names) {

        Queue<Player> players = new LinkedList<>();

        for (String name : names) {
            players.add(new Player(name));
        }

        return players;
    }
}
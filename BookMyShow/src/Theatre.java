import java.util.*;

public class Theatre {
    String id, name, city;
    List<Screen> screens = new ArrayList<>();

    public Theatre(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
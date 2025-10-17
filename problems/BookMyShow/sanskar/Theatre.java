package lld.problems.BookMyShow.sanskar;
import java.util.ArrayList;
import java.util.List;

public class Theatre {
    String name;
    String location;
    List<Screen> screens;

    Theatre(String name, String location){
        this.name = name;
        this.location = location;
        this.screens = new ArrayList<>();
    }

    Theatre(String name, String location, List<Screen> screens){
        this.name = name;
        this.location = location;
        this.screens = screens;
    }

    public void addScreen(Screen s){
        screens.add(s);
    }

    public void removeScreen(Screen s){
        screens.remove(s);
    }
}

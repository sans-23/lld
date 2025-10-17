package lld.problems.BookMyShow.sanskar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Screen {
    List<Show> shows;
    List<Seat> seatingArrangement;

    Screen(List<Seat> seatingArrangement){
        this.seatingArrangement = seatingArrangement;
        this.shows = new ArrayList<>();
    }

    public void addShow(Movie movie, LocalDate date){
        Show show = new Show(movie, seatingArrangement, date);
        shows.add(show);
    }
}

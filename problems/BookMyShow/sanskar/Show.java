package lld.problems.BookMyShow.sanskar;

import java.time.LocalDate;
import java.util.List;

public class Show {
    List<Seat> seats;
    Movie movie;
    LocalDate date;

    Show(Movie movie, List<Seat> seats, LocalDate date){
        this.movie = movie;
        this.seats = seats;
        this.date = date;
    }
}

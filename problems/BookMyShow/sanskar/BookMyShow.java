package lld.problems.BookMyShow.sanskar;

import java.util.ArrayList;
import java.util.List;

// singleton pattern
public class BookMyShow {
    public static BookMyShow bookMyShow;
    List<User> users;
    List<Theatre> theatres;

    private BookMyShow(){
        this.users = new ArrayList<>();
        this.theatres = new ArrayList<>();
    }

    public static BookMyShow init(){
        if(bookMyShow==null){
            bookMyShow = new BookMyShow();
        }
        return bookMyShow;
    }

    public void addUser(User u){
        users.add(u);
    }

    public void addTheatre(Theatre t){
        theatres.add(t);
    }
}

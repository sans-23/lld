package lld.problems.BookMyShow.sanskar;

public class Main {
    public static void main(String[] args) {
        BookMyShow bookMyShow = BookMyShow.init();

        bookMyShow.addUser(new User("sanskar", "sa@gmail.com"));
        bookMyShow.addUser(new User("sans", "sass@gmail.com"));

        bookMyShow.addTheatre(new Theatre("JP MALL", "jp nagar"));
        bookMyShow.addTheatre(new Theatre("KORA MALL", "koramangla"));

    }
}

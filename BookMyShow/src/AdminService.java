import java.util.*;

public class AdminService {

    List<Movie> movies = new ArrayList<>();
    List<Theatre> theatres = new ArrayList<>();
    List<Show> shows = new ArrayList<>();

    public void addMovie(Admin admin, Movie movie) {
        validateAdmin(admin);
        movies.add(movie);
        System.out.println("Movie added: " + movie.title);
    }

    public void addTheatre(Admin admin, Theatre theatre) {
        validateAdmin(admin);
        theatres.add(theatre);
        System.out.println("Theatre added: " + theatre.name);
    }

    public void addMovieShow(Admin admin, Show show) {
        validateAdmin(admin);
        shows.add(show);
        System.out.println("Show added for movie: " + show.movie.title);
    }

    private void validateAdmin(Admin admin) {
        if (admin == null) {
            throw new RuntimeException("Unauthorized: Admin required");
        }
    }
}
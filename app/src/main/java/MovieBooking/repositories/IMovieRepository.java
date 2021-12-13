package MovieBooking.repositories;

import MovieBooking.entities.Movie;
import java.util.List;

public interface IMovieRepository {
    public List<Movie> getAllMoveis();
    public Movie getMovieById(String id);
    public void saveMovie(Movie movie);
}

package MovieBooking.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import MovieBooking.entities.Movie;

public class MovieRepository implements IMovieRepository{
    private final Map<String, Movie> movieMap;

    public MovieRepository(){
        movieMap = new HashMap<>();
    }
    public MovieRepository(Map<String, Movie> map) {
        movieMap = map;
    }
    @Override
    public List<Movie> getAllMoveis() {
        return new ArrayList<>(movieMap.values());
    }
    @Override
    public Movie getMovieById(String id) {
        return movieMap.get(id);
    }
    @Override
    public void saveMovie(Movie movie) {
        movieMap.put(movie.getId(), movie);
    }

    
}

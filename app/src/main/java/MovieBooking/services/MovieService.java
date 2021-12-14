package MovieBooking.services;

import java.util.List;

import MovieBooking.entities.Movie;
import MovieBooking.repositories.IMovieRepository;

public class MovieService implements IMovieService{

    IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovieList() {
        return movieRepository.getAllMoveis();
    }
    
}

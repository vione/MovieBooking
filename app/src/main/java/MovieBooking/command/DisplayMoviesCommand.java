package MovieBooking.command;

import java.util.List;

import MovieBooking.entities.Movie;
import MovieBooking.services.IMovieService;

public class DisplayMoviesCommand implements ICommand {

    IMovieService iMovieService;
    

    public DisplayMoviesCommand(IMovieService iMovieService) {
        this.iMovieService = iMovieService;
    }


    @Override
    public void execute(List<String> tokens) {
        List<Movie> movieList = iMovieService.getMovieList();
        for(Movie movie: movieList){
            System.out.println("Movie Id - "+movie.getId());
            System.out.println("Movie Name - "+movie.getTitle());
            System.out.println("Movie Duration - "+movie.getDurationInMins());
            System.out.println();
        }
        
    }
    
}

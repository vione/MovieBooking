package MovieBooking.services;

import java.util.ArrayList;
import java.util.List;

import MovieBooking.dtos.ShowResponse;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;
import MovieBooking.repositories.IShowRepository;
import MovieBooking.repositories.IShowSeatRepository;

public class ShowService implements IShowService {

    private final IShowRepository showRepository;
    private final IShowSeatRepository showSeatRepository;

    public ShowService(IShowRepository showRepository,IShowSeatRepository showSeatRepository){
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public List<ShowResponse> getAllShowsByMovie(String movieTitle) {
        List<ShowResponse> response = new ArrayList<>();
        List<Show> showList =  showRepository.getAllShowByMovieName(movieTitle);
        for(Show show: showList){
            response.add(new ShowResponse(
                show.getId(), show.getStart(), show.getEnd(), show.getScreenName(), 
                show.getCinemaName(), show.getMovieName()));
        }
        return response;
    }

    @Override
    public List<ShowSeat> getSeatListByShowId(String showId) {
        return showSeatRepository.getListOfShowSeatsByShowId(showId);
    }
    
}

package MovieBooking.services;

import java.util.List;

import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;

public interface IShowService {
    List<Show> getAllShowsByMovie(String movieTitle);
    List<ShowSeat> getSeatListByShowId(String showId);
}

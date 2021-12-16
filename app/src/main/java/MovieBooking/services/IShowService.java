package MovieBooking.services;

import java.util.List;

import MovieBooking.dtos.ShowResponse;
import MovieBooking.entities.ShowSeat;

public interface IShowService {
    List<ShowResponse> getAllShowsByMovie(String movieTitle);
    List<ShowSeat> getSeatListByShowId(String showId);
}

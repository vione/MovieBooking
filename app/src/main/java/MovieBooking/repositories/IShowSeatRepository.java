package MovieBooking.repositories;

import java.util.List;

import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;

public interface IShowSeatRepository {
    public ShowSeat getShowSeat(String showId,String id);
    public List<ShowSeat> getListOfShowSeatsByShowId(String showId);
    public void addshowSeats(Show show, List<Seat> seatList);
    public void updateShowSeat(ShowSeat showSeat);

    
}

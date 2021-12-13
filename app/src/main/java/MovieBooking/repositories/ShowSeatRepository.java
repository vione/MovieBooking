package MovieBooking.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;

public class ShowSeatRepository implements IShowSeatRepository {

    private final Map<String, ShowSeat> showSeatMap;

    public ShowSeatRepository() {
        showSeatMap = new HashMap<>();
    }

    public ShowSeatRepository(Map<String,ShowSeat> seatMap) {
        showSeatMap = seatMap;
    }

    @Override
    public ShowSeat getShowSeat(String showId, String id) {
        return showSeatMap.get(showId + "#" + id);
    }

    @Override
    public List<ShowSeat> getListOfShowSeatsByShowId(String showId) {
        return showSeatMap.values()
                .stream()
                .filter(s -> {String sId = s.getId().split("#")[0];
                        return showId.equals(sId);})
                .collect(Collectors.toList());
    }

    @Override
    public void addshowSeats(Show show, List<Seat> seatList) {
        seatList.stream()
            .forEach(seat -> {
                String id = show.getId() + "#" + seat.getId();
                showSeatMap.put(id, new ShowSeat(id, seat, show));
            });
        
    }

    @Override
    public void updateShowSeat(ShowSeat showSeat) {
        showSeatMap.put(showSeat.getId(),showSeat);
    }
}

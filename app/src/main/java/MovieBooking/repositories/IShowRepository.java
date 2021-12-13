package MovieBooking.repositories;

import java.util.List;
import MovieBooking.entities.Show;

public interface IShowRepository {
    public List<Show> getAllShowByMovieName(String Title);
    public Show getShowById(String Id);
    public void saveShow(Show show);
}

package MovieBooking.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import MovieBooking.entities.Show;

public class ShowRepository implements IShowRepository{

    private final Map<String, Show> showMap;

    public ShowRepository(){
        showMap = new HashMap<>();
    }

    public ShowRepository(Map<String, Show> map) {
        showMap = map;
    }

    @Override
    public List<Show> getAllShowByMovieName(String Title) {
       return showMap.values().stream()
            .filter(show -> show.getMovieName().equals(Title))
            .collect(Collectors.toList());

    }

    @Override
    public Show getShowById(String Id) {
        return showMap.get(Id);
    }

    @Override
    public void saveShow(Show show) {
        showMap.put(show.getId(), show);
    }
    
}

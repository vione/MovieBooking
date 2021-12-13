package MovieBooking.repositories;

import java.util.HashMap;
import java.util.Map;
import MovieBooking.entities.Cinema;

public class CinemaRepository implements ICinemaRepository{
    private final Map<String, Cinema> cinemaMap;

    public CinemaRepository(){
        cinemaMap = new HashMap<>();
    }

    public CinemaRepository(Map<String, Cinema> map){
        cinemaMap = map;
    }

    @Override
    public Cinema getCinemaById(String id) {
        return cinemaMap.get(id);
    }

    @Override
    public Cinema getcinemaByName(String name) {
        return cinemaMap.values().stream()
                .filter(cinema -> cinema.getName().equals(name))
                .findAny().orElse(null);
    }

    @Override
    public void update(Cinema cinema) {
        cinemaMap.put(cinema.getId(), cinema);
        
    }

    @Override
    public void save(Cinema cinema) {
        cinemaMap.put(cinema.getId(), cinema);
        
    }

    
}

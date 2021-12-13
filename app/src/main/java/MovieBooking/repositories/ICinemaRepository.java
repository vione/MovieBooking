package MovieBooking.repositories;

import MovieBooking.entities.Cinema;

public interface ICinemaRepository {
    public Cinema getCinemaById(String id);
    public Cinema getcinemaByName(String name);
    public void update(Cinema cinema);
    public void save(Cinema cinema);
}

package MovieBooking.repositories;

import java.util.List;

import MovieBooking.entities.Customer;
import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.Ticket;

public interface ITicketRepository {
    public Ticket getTicketById(String Id);
    public Ticket saveTicket(Customer customer, Show show, List<Seat> seatList);
    public void removeTicket(String Id);
}

package MovieBooking.repositories;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import MovieBooking.entities.Customer;
import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.Ticket;

public class TicketRepository implements ITicketRepository {

    private final Map<String, Ticket> ticketMap;
    private static Integer counter = 0;

    public TicketRepository() {
        ticketMap = new HashMap<>();
    }

    public TicketRepository(Map<String, Ticket> map, Integer counter) {
        ticketMap = map;
        TicketRepository.counter = counter;
    }

    @Override
    public Ticket getTicketById(String Id) {
        return ticketMap.get(Id);
    }


    @Override
    public void removeTicket(String Id) {
        ticketMap.remove(Id);
    }

    @Override
    public Ticket saveTicket(Customer customer, Show show, List<Seat> seatList) {
        String id = (++counter).toString();
        Ticket ticket = new Ticket(id, customer, show, seatList);
        ticketMap.put(id,ticket);
        return ticket;
    }
    
}

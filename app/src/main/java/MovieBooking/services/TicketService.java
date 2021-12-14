package MovieBooking.services;

import java.util.List;

import MovieBooking.entities.Customer;
import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;
import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.NoSuchTicketFoundException;
import MovieBooking.exceptions.SeatNotAvailableException;
import MovieBooking.repositories.ICustomerRepository;
import MovieBooking.repositories.IShowRepository;
import MovieBooking.repositories.IShowSeatRepository;
import MovieBooking.repositories.ITicketRepository;

public class TicketService implements ITicketService {
    private final ITicketRepository ticketRepository;
    private final IShowRepository showRepository;
    private final IShowSeatRepository showSeatRepository;
    private final ICustomerRepository customerRepository;

    public TicketService(ITicketRepository ticketRepository, IShowRepository showRepository,
            IShowSeatRepository showSeatRepository, ICustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Ticket bookTicket(String customerId, String showId, List<Seat> seatList) throws SeatNotAvailableException {
        Customer customer = customerRepository.getCustomerByID(customerId);
        Show show = showRepository.getShowById(showId);
        for(Seat seat: seatList){
            ShowSeat showSeat = showSeatRepository.getShowSeat(showId, seat.getId());
            if(showSeat.isLocked()) throw new SeatNotAvailableException();
        }
        for (Seat seat: seatList){
            ShowSeat showSeat = showSeatRepository.getShowSeat(showId, seat.getId());
            showSeat.lock();
            showSeatRepository.updateShowSeat(showSeat);
        }
        Ticket ticket = ticketRepository.saveTicket(customer, show, seatList);
        return ticket;
    }

    @Override
    public void cancelTicket(String ticketID) throws NoSuchTicketFoundException {
        Ticket ticket = ticketRepository.getTicketById(ticketID);
        if(ticket == null) throw  new NoSuchTicketFoundException();
        List<Seat> seatList = ticket.getSeatList();
        String showId = ticket.getShowId();
        for (Seat seat: seatList){
            ShowSeat showSeat = showSeatRepository.getShowSeat(showId, seat.getId());
            showSeat.unlock();
            showSeatRepository.updateShowSeat(showSeat);
        }
        ticketRepository.removeTicket(ticketID);
    }
    
}

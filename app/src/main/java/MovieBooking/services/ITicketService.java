package MovieBooking.services;

import java.util.List;

import MovieBooking.entities.Seat;
import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.NoSuchTicketFoundException;
import MovieBooking.exceptions.SeatNotAvailableException;

public interface ITicketService {
    Ticket bookTicket(String customerId, String showId, List<Seat> seatList) throws SeatNotAvailableException;
    //Giving here the list of seat, service will match with show Id and seat List
    void cancelTicket(String ticketID) throws NoSuchTicketFoundException;
}
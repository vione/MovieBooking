package MovieBooking.command;

import java.util.ArrayList;
import java.util.List;

import MovieBooking.entities.Seat;
import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.SeatNotAvailableException;
import MovieBooking.services.ITicketService;

public class BookTicketCommand implements ICommand{

    private final ITicketService iTicketService;

    public BookTicketCommand(ITicketService iTicketService){
        this.iTicketService = iTicketService;
    }

    @Override
    public void execute(List<String> tokens) {

        //ticket syntax bookTicket(customerId, ShowId, seatList)
        String showId = tokens.get(2);
        String customerId = tokens.get(1);
        List<Seat> seatList = new ArrayList<>();
        for(int i= 3; i <  tokens.size();i++){
            String[] words = tokens.get(i).split("#");
            Seat seat = new Seat(tokens.get(i), Integer.parseInt(words[0]), Integer.parseInt(words[1]));
            seatList.add(seat);
        }
        try {
            Ticket ticket = iTicketService.bookTicket(customerId, showId, seatList);
            System.out.println("TicketId - "+ticket.getId());
            System.out.println();
        } catch (SeatNotAvailableException e) {
            System.out.println(e);;
        }
        
    }
    
}

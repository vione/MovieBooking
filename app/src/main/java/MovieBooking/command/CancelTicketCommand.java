package MovieBooking.command;

import java.util.List;

import MovieBooking.exceptions.NoSuchTicketFoundException;
import MovieBooking.services.ITicketService;

public class CancelTicketCommand implements ICommand{
    private final ITicketService iTicketService;

    public CancelTicketCommand(ITicketService iTicketService) {
        this.iTicketService = iTicketService;
    }

    @Override
    public void execute(List<String> tokens) {
        String ticketId = tokens.get(1);
        try{
            iTicketService.cancelTicket(ticketId);
            System.out.println("Ticket has been cancelled");
            System.out.println();
        } catch(NoSuchTicketFoundException e){
            System.out.println(e);
        }
        
    }
    
}

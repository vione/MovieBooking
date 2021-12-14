package MovieBooking.exceptions;

public class NoSuchTicketFoundException extends Exception {
    
    @Override
    public String toString() {
        return "No such ticket found";
    }
}

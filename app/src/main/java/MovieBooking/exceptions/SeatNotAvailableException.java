package MovieBooking.exceptions;

public class SeatNotAvailableException extends Exception{
    
    @Override
    public String toString(){
        return "Some Seats have been booked! Please try booking available seats";
    }
}

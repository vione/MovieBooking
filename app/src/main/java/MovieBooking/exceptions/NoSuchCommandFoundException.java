package MovieBooking.exceptions;

public class NoSuchCommandFoundException extends Exception{

    @Override
    public String toString(){
        return "No such command found";
    }
    
}

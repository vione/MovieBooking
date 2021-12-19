package MovieBooking.exceptions;

public class NoSuchDataFoundException extends Exception{

    @Override
    public String toString(){
        return "The Data you have provided does not exist|";
    }
    
}

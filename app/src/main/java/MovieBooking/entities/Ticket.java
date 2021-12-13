package MovieBooking.entities;

import java.util.List;

public class Ticket {
    private final String id;
    private final Customer customer;
    private final Show show;
    private final List<Seat> seatList;
    
    public Ticket(String id, Customer customer, Show show, List<Seat> seatList) {
        this.id = id;
        this.customer = customer;
        this.show = show;
        this.seatList = seatList;
    }

    public String getId(){
        return id;
    }
    public String getCustomerName() {
        return customer.getName();
    }
    public String getShowId() {
        return show.getId();
    }
    public List<Seat> getShowList() {
        return seatList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

    
    
}

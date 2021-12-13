package MovieBooking.entities;
import java.util.ArrayList;
import java.util.List;

public class Screen {

    private final String id;
    private final String name;
    private final List<Seat> seatList;
    
    public Screen(String id, String name) {
        this.id = id;
        this.name = name;
        this.seatList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public boolean addSeat(Seat seat){
        return seatList.add(seat);
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
        Screen other = (Screen) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }     
}

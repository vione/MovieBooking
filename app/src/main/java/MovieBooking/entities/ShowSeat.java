package MovieBooking.entities;


public class ShowSeat {
    private final String id;
    private final Seat seat;
    private final Show show;
    private ShowSeatStatus status;
    
    public ShowSeat(String id, Seat seat, Show show) {
        this.id = id;
        this.seat = seat;
        this.show = show;
        this.status = ShowSeatStatus.UNRESERVED;
    }
    
    public String getId() {
        return id;
    }
    public Seat getSeat() {
        return seat;
    }
    public Show getShow() {
        return show;
    }
    public ShowSeatStatus getStatus() {
        return status;
    }
    public int getSeatRow() {
        return seat.getSeatRow();
    }
    public int getSeatColumn() {
        return seat.getSeatColumn();
    }
    public void lock() {
        status = ShowSeatStatus.RESERVED;
    }
    public void unlock() {
        status = ShowSeatStatus.UNRESERVED;
    }
    public boolean isLocked() {
        return status == ShowSeatStatus.RESERVED;
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
        ShowSeat other = (ShowSeat) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }




    



    
}

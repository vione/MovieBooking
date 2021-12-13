package MovieBooking.entities;

public class Seat {
    private final String id;
    private final int seatRow;
    private final int seatColumn;
    
    public String getId() {
        return id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public Seat(String id, int seatRow, int seatColumn) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
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
        Seat other = (Seat) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

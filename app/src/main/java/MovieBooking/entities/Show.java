package MovieBooking.entities;
import java.util.Date;

public class Show {

    private final String id;
    private final Date start;
    private final Date end;
    private final Screen screen;
    private final Cinema cinema;
    private final Movie movie;
    
    public Show(String id, Date start, Date end, Screen screen, Cinema cinema, Movie movie) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.screen = screen;
        this.cinema = cinema;
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getScreenName() {
        return screen.getName();
    }

    public String getCinemaName() {
        return cinema.getName();
    }

    public String getMovieName() {
        return movie.getTitle();
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
        Show other = (Show) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}

package MovieBooking.dtos;

import java.util.Date;

public class ShowResponse {
    private final String showId;
    private final Date start;
    private final Date end;
    private final String screenName;
    private final String cinemaName;
    private final String movieName;
    
    public ShowResponse(String showId, Date start, Date end, String screenName, String cinemaName, String movieName) {
        this.showId = showId;
        this.start = start;
        this.end = end;
        this.screenName = screenName;
        this.cinemaName = cinemaName;
        this.movieName = movieName;
    }

    public String getShowId() {
        return showId;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getMovieName() {
        return movieName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((showId == null) ? 0 : showId.hashCode());
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
        ShowResponse other = (ShowResponse) obj;
        if (showId == null) {
            if (other.showId != null)
                return false;
        } else if (!showId.equals(other.showId))
            return false;
        return true;
    }
    
}

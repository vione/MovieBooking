package MovieBooking.entities;

public class Movie {
    private final String id;
    private final String title;
    private final int durationInMins;
    
    public Movie(String id, String title, int durationInMins) {
        this.id = id;
        this.title = title;
        this.durationInMins = durationInMins;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInMins() {
        return durationInMins;
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
        Movie other = (Movie) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    

    
    
}

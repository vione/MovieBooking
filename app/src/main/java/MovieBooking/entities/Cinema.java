package MovieBooking.entities;

import java.util.HashMap;

public class Cinema {
    private final String id;
    private final String name;
    private final HashMap<String, Screen> screenMap;
    public Cinema(String id, String name) {
    
        this.id = id;
        this.name = name;
        this.screenMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addScreen(Screen screen){
         this.screenMap.put(screen.getId(), screen);
    }

    public Screen getScreenByName(String screenName){
        return screenMap.values()
                .stream().filter(screen -> screenName.equals(screen.getName()))
                .findAny()
                .orElse(null);    
    }

    public Screen getScreenById(String screenId){
        return screenMap.get(screenId);
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
        Cinema other = (Cinema) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

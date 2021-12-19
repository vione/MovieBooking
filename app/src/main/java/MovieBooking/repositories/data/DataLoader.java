package MovieBooking.repositories.data;

import java.util.HashMap;
import java.util.Map;

import MovieBooking.exceptions.NoSuchDataFoundException;

public class DataLoader {

    private static final Map<String,Idata> dataMap =  new HashMap<>();

    public void register(String dataName, Idata data){
        dataMap.put(dataName, data);
    }

    private Idata get(String dataName){
        return dataMap.get(dataName);
    }

    public void executeData(String dataName, String dataPath) throws NoSuchDataFoundException{
        Idata data = this.get(dataName);
        if(data == null) throw new NoSuchDataFoundException();
        data.loadData(dataPath, ",");
    }

    
}

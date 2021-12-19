package MovieBooking.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import MovieBooking.entities.Cinema;
import MovieBooking.repositories.ICinemaRepository;

public class CinemaData implements Idata{

    private final ICinemaRepository iCinemaRepository;

    public CinemaData(ICinemaRepository iCinemaRepository) {
        this.iCinemaRepository = iCinemaRepository;
    }

    @Override
    public void loadData(String datapath, String delimeter) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(datapath));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(delimeter));
                addCinema(tokens.get(0), tokens.get(1));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void addCinema(String cinemaId, String CinemaName){
        iCinemaRepository.save(new Cinema(cinemaId, CinemaName));
    }


    
}

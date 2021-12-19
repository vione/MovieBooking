package MovieBooking.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import MovieBooking.entities.Cinema;
import MovieBooking.entities.Screen;
import MovieBooking.repositories.ICinemaRepository;

public class ScreenData implements Idata{

    private final ICinemaRepository iCinemaRepository;

    public ScreenData(ICinemaRepository iCinemaRepository){
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
                addScreen(tokens.get(0),tokens.get(1), tokens.get(2));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void addScreen(String screenId, String screenName, String cinemaId){
        Cinema cinema = iCinemaRepository.getCinemaById(cinemaId);
        cinema.addScreen(new Screen(screenId, screenName));
        iCinemaRepository.update(cinema);
    }
    
}

package MovieBooking.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import MovieBooking.entities.Cinema;
import MovieBooking.entities.Screen;
import MovieBooking.entities.Seat;
import MovieBooking.repositories.ICinemaRepository;

public class SeatData implements Idata{

    private final ICinemaRepository iCinemaRepository;

    public SeatData(ICinemaRepository iCinemaRepository) {
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
                addSeat(tokens.get(0), tokens.get(1), Integer.parseInt(tokens.get(2)), 
                    Integer.parseInt(tokens.get(3)));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    private void addSeat(String cinemaId, String screenId, Integer numRows, Integer numColumns){
        Cinema cinema = iCinemaRepository.getCinemaById(cinemaId);
        Screen screen = cinema.getScreenById(screenId);
        for(int i = 1; i<= numRows;i++){
            for( int j = 1; j <=numColumns;j++){
                screen.addSeat(new Seat(i + "#" + j, i, j));
            }
        }
        iCinemaRepository.update(cinema);
    }
    
    
}

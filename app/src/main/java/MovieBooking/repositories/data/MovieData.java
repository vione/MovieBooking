package MovieBooking.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import MovieBooking.entities.Movie;
import MovieBooking.repositories.IMovieRepository;

public class MovieData implements Idata{
    
    private final IMovieRepository iMovieRepository;

    public MovieData(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    @Override
    public void loadData(String datapath, String delimeter) {
        
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(datapath));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(delimeter));
                addMovie(tokens.get(0), tokens.get(1), Integer.parseInt(tokens.get(2)));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    private void addMovie(String movieId, String movieName, int duration){
        iMovieRepository.saveMovie(new Movie(movieId, movieName, duration));
    }
}

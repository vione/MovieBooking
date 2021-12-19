package MovieBooking.repositories.data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import MovieBooking.entities.Cinema;
import MovieBooking.entities.Movie;
import MovieBooking.entities.Screen;
import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.repositories.ICinemaRepository;
import MovieBooking.repositories.IMovieRepository;
import MovieBooking.repositories.IShowRepository;
import MovieBooking.repositories.IShowSeatRepository;

public class ShowData implements Idata{

    private final IShowRepository iShowRepository;
    private final IMovieRepository iMovieRepository;
    private final ICinemaRepository iCinemaRepository;
    private final IShowSeatRepository iShowSeatRepository;

    

    public ShowData(IShowRepository iShowRepository, IMovieRepository iMovieRepository,
            ICinemaRepository iCinemaRepository, IShowSeatRepository iShowSeatRepository) {
        this.iShowRepository = iShowRepository;
        this.iMovieRepository = iMovieRepository;
        this.iCinemaRepository = iCinemaRepository;
        this.iShowSeatRepository = iShowSeatRepository;
    }

    @Override
    public void loadData(String datapath, String delimeter) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(datapath));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(delimeter));
                addShow(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), 
                tokens.get(4), tokens.get(5));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        
    }

    private void addShow(String showId, String movieId, String CinemaId, String ScreeId, String start, String end) throws ParseException{
        Cinema cinema = iCinemaRepository.getCinemaById(CinemaId);
        Screen screen = cinema.getScreenById(ScreeId);
        Movie movie = iMovieRepository.getMovieById(movieId);
        Date startTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(start);
        Date endTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(end);
        Show show = new Show(showId, startTime, endTime, screen, cinema, movie);
        iShowRepository.saveShow(show);
        List<Seat> seatList = screen.getSeatList();
        iShowSeatRepository.addshowSeats(show, seatList);
    }
    
}

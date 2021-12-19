package MovieBooking.command;

import java.text.SimpleDateFormat;
import java.util.List;

import MovieBooking.dtos.ShowResponse;
import MovieBooking.services.IShowService;

public class DisplayShowCommand implements ICommand{
    IShowService iShowService;

    public DisplayShowCommand(IShowService iShowService) {
        this.iShowService = iShowService;
    }

    @Override
    public void execute(List<String> tokens) {
        String movieTitle = tokens.get(1);
        List<ShowResponse> showList = iShowService.getAllShowsByMovie(movieTitle);
        for(ShowResponse showResponse: showList){
            System.out.println("Show Id - "+showResponse.getShowId());
            System.out.println("Movie Title - "+showResponse.getMovieName());
            System.out.println("Start Time - "+
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(showResponse.getStart()));
            System.out.println("End Time - "+
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(showResponse.getEnd()));
            System.out.println("Cinema Name - "+showResponse.getCinemaName());
            System.out.println("Screen Name - "+showResponse.getScreenName());
            System.out.println();
        }
        
    }
    
}

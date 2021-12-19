package MovieBooking.config;

import MovieBooking.command.*;
import MovieBooking.repositories.*;
import MovieBooking.repositories.data.*;
import MovieBooking.services.*;



public class ApplicationConfiguration {

    /*
    This configuration File configures all that needs to be intilaized before the application
    is started. There are following configurations need to be made.
    1. Initialize Repositories
    2. Initialize Services
    3. Initialize CommandInvoker
    4. Initialize DataLoader 
    5. Registiering Commands
    6. Registering Data Objects
    */

    //Initializing Repositories first cause even services need them.
    //All they might take a map as input but here we are not providing any.
    private final ICustomerRepository iCustomerRepository = new CustomerRepository();
    private final IMovieRepository iMovieRepository = new MovieRepository();
    private final ICinemaRepository iCinemaRepository = new CinemaRepository();
    private final IShowRepository iShowRepository = new ShowRepository();
    private final IShowSeatRepository iShowSeatRepository = new ShowSeatRepository();
    private final ITicketRepository iTicketRepository = new TicketRepository();

    //We have 3 services to initialize to service 5 commands
    private final IMovieService iMovieService = new MovieService(iMovieRepository);
    private final IShowService iShowService = new ShowService(iShowRepository, iShowSeatRepository);
    private final ITicketService iTicketService = new TicketService(iTicketRepository, 
        iShowRepository, iShowSeatRepository, iCustomerRepository);

    //Initializing 5 commands
    private final ICommand bookTickeCommand = new BookTicketCommand(iTicketService);
    private final ICommand cancelTikcetCommand = new CancelTicketCommand(iTicketService);
    private final ICommand displayMoviesCommand = new DisplayMoviesCommand(iMovieService);
    private final ICommand displayShowCommand = new DisplayShowCommand(iShowService);
    private final ICommand displayShowSeatcCommand = new DisplayShowSeatCommand(iShowService);

    //Initiazing DataLoader and CommandInvoker
    private final CommandInvoker commandInvoker = new CommandInvoker();
    private final DataLoader dataLoader = new DataLoader();

    //registering Commands and Data Objects
    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("BOOK-TICKET", bookTickeCommand);
        commandInvoker.register("CANCEL-TICKET", cancelTikcetCommand);
        commandInvoker.register("DISPLAY-MOVIES", displayMoviesCommand);
        commandInvoker.register("DISPLAY-SHOWS", displayShowCommand);
        commandInvoker.register("DISPLAY-SEATS", displayShowSeatcCommand);
        return commandInvoker;
    }

    public DataLoader getDataLoader(){
        dataLoader.register("CINEMA-DATA", new CinemaData(iCinemaRepository));
        dataLoader.register("SCREEN-DATA", new ScreenData(iCinemaRepository));
        dataLoader.register("CUSTOMER-DATA", new CustomerData(iCustomerRepository));
        dataLoader.register("MOVIE-DATA", new MovieData(iMovieRepository));
        dataLoader.register("SEAT-DATA", new SeatData(iCinemaRepository));
        dataLoader.register("SHOW-DATA", new ShowData(iShowRepository, iMovieRepository, iCinemaRepository, iShowSeatRepository));
        return dataLoader;
    }

}

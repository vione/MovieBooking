package MovieBooking.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import MovieBooking.dtos.ShowResponse;
import MovieBooking.entities.*;
import MovieBooking.repositories.ShowRepository;
import MovieBooking.repositories.ShowSeatRepository;


@DisplayName("Show Service Test")
@ExtendWith(MockitoExtension.class)
public class ShowServiceTest {
    
    @Mock
    ShowRepository showRepositoryMock;

    @Mock
    ShowSeatRepository showSeatRepositoryMock;

    @InjectMocks
    ShowService showService;

    @Test
    @DisplayName("Testing getAllShowsByMovie. Should return list of showresponses")
    public void getAllMoveisTest_shouldReturnShowResponseList() throws ParseException {
        //Arrange
        List<Show> showList = new ArrayList<>(
                List.of(
                        new Show("1",
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 13:30"),
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 16:00"),
                                new Screen("1","ScreenA"),
                                new Cinema("1","CinemaA"),
                                new Movie("1","MovieA",120)),
                        new Show("3",
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 12:30"),
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 15:00"),
                                new Screen("4","ScreenA"),
                                new Cinema("2","CinemaB"),
                                new Movie("1","MovieA",120))
                        )
        );

        List<ShowResponse> actualShowResponseList = new ArrayList<>(
                List.of(
                        new ShowResponse("1",
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 13:30"), 
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 16:00"), 
                                "ScreenA", 
                                "CinemaA", 
                                "MovieA"),
                        new ShowResponse(
                                "3",
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 12:30"),
                                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 15:00"),
                                "ScreenA",
                                "CinemaB",
                                "MovieA"
                        )
                )
        );
        when(showRepositoryMock.getAllShowByMovieName("MovieA")).thenReturn(showList);

        //Act
        List<ShowResponse> expectedList = showService.getAllShowsByMovie("MovieA");

        //Assert
        Assertions.assertEquals(expectedList, actualShowResponseList);

        verify(showRepositoryMock).getAllShowByMovieName("MovieA");
    }

    @Test
    @DisplayName("getAllShowsByMovieTitle method Should Return Empty ShowResponse List If No Movies Found For Given Movie Title")
    public void getAllShowsByMovieTitle_GivenMovieTitle_ShouldReturnEmptyShowResponseListIfNoMoviesFound() throws ParseException {
        //Arrange
        List<Show> showList = new ArrayList<>();
        List<ShowResponse> actualShowResponseList = new ArrayList<>();

        when(showRepositoryMock.getAllShowByMovieName("MovieA")).thenReturn(showList);

        //Act
        List<ShowResponse> expectedShowList = showService.getAllShowsByMovie("MovieA");

        //Assert
        Assertions.assertEquals(expectedShowList,actualShowResponseList);

        verify(showRepositoryMock).getAllShowByMovieName("MovieA");
    }

    @Test
    @DisplayName("getAllShowSeats method Should Return ShowSeat List Given Show Id")
    public void getAllShowSeats_GivenShowId_ShouldReturnShowSeatList() throws ParseException {
        //Arrange
        Show show  = new Show("1",
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 13:30"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 16:00"),
                new Screen("1","ScreenA"),
                new Cinema("1","CinemaA"),
                new Movie("1","MovieA",120));

        List<ShowSeat> actualShowSeatList = new ArrayList<>(
                List.of(
                        new ShowSeat("1#1#1",new Seat("1#1",1,1),show),
                        new ShowSeat("1#1#2",new Seat("1#2",1,2),show),
                        new ShowSeat("1#1#3",new Seat("1#3",1,3),show),
                        new ShowSeat("1#2#1",new Seat("2#1",2,1),show),
                        new ShowSeat("1#2#2",new Seat("2#2",2,2),show),
                        new ShowSeat("1#2#3",new Seat("2#3",2,3),show),
                        new ShowSeat("1#3#1",new Seat("3#1",3,1),show),
                        new ShowSeat("1#3#2",new Seat("3#2",3,2),show),
                        new ShowSeat("1#3#3",new Seat("3#3",3,3),show)
                )
        );

        when(showSeatRepositoryMock.getListOfShowSeatsByShowId("1")).thenReturn(actualShowSeatList);

        //Act
        List<ShowSeat> expectedShowSeatList = showService.getSeatListByShowId("1");

        //Assert
        Assertions.assertEquals(expectedShowSeatList,actualShowSeatList);
        verify(showSeatRepositoryMock).getListOfShowSeatsByShowId("1");
    }
}

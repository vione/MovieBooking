package MovieBooking.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;
import MovieBooking.services.ShowService;

@DisplayName("Testing Display Show Seat Command")
@ExtendWith(MockitoExtension.class)
public class DisplayShowSeatCommandTest {

    @Mock
    ShowService showServiceMock;

    @Mock
    Show show;

    @InjectMocks
    DisplayShowSeatCommand displayShowSeatCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("execute - should print the seats for the show")
    public void excecute_ShouldPrintSeatList(){

        //Arrange
        String showId = "1";
        List<ShowSeat> showSeatList = new ArrayList<>(
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
        when(showServiceMock.getSeatListByShowId(showId)).thenReturn(showSeatList);
        String expectedOutput = "SeatRow - 1\n" +
                "SeatColumn - 1\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 1\n" +
                "SeatColumn - 2\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 1\n" +
                "SeatColumn - 3\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 2\n" +
                "SeatColumn - 1\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 2\n" +
                "SeatColumn - 2\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 2\n" +
                "SeatColumn - 3\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 3\n" +
                "SeatColumn - 1\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 3\n" +
                "SeatColumn - 2\n" +
                "Status - UNRESERVED\n" +
                "\n" +
                "SeatRow - 3\n" +
                "SeatColumn - 3\n" +
                "Status - UNRESERVED";


        //Act
        displayShowSeatCommand.execute(new ArrayList<>(List.of("DISPLAY-SEATS", showId)));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());

        verify(showServiceMock).getSeatListByShowId("1");

    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
    }
    
}

package MovieBooking.command;

import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import MovieBooking.dtos.ShowResponse;
import MovieBooking.services.ShowService;

@DisplayName("Display Show Command Test")
@ExtendWith(MockitoExtension.class)
public class DisplayShowCommandTest {
    @Mock
    ShowService showServiceMock;

    @InjectMocks
    DisplayShowCommand displayShowCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("execute Should print the show response")
    public void execute_ShoudlPrintTheShowResponse() throws ParseException{

        //Arrange
        String movieTitle = "MovieA";
        List<ShowResponse> showResponseList = new ArrayList<>();

        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 10:30");
        Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 13:00");

        //ShowResponse(showId, start, end, screenName, cinemaName, movieName)
        showResponseList.add(new ShowResponse("1",start,end, "ScreenA", "CinemaA", "MovieA"));
        showResponseList.add(new ShowResponse("2",start,end, "ScreenB", "CinemaA", "MovieA"));
        showResponseList.add(new ShowResponse("3",start,end, "ScreenC", "CinemaA", "MovieA"));

        when(showServiceMock.getAllShowsByMovie(movieTitle)).thenReturn(showResponseList);

        String expectedOutput = "Show Id - 1\n" +
                "Movie Title - MovieA\n" +
                "Start Time - 20/07/2021 10:30\n" +
                "End Time - 20/07/2021 13:00\n" +
                "Cinema Name - CinemaA\n" +
                "Screen Name - ScreenA\n" +
                "\n" + 
                "Show Id - 2\n" +
                "Movie Title - MovieA\n" +
                "Start Time - 20/07/2021 10:30\n" +
                "End Time - 20/07/2021 13:00\n" +
                "Cinema Name - CinemaA\n" +
                "Screen Name - ScreenB\n" +
                "\n"+
                "Show Id - 3\n" +
                "Movie Title - MovieA\n" +
                "Start Time - 20/07/2021 10:30\n" +
                "End Time - 20/07/2021 13:00\n" +
                "Cinema Name - CinemaA\n" +
                "Screen Name - ScreenC";

        //Act
        displayShowCommand.execute(new ArrayList<>(List.of("DISPLAY-SHOWS","MovieA")));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    @DisplayName("execute should resturn an empty list when there is no match")
    public void execute_ShoulReturnEmptyString(){
        String movieTitle = "MovieA";
        List<ShowResponse> showResponseList = new ArrayList<>();
        when(showServiceMock.getAllShowsByMovie("MovieA")).thenReturn(showResponseList);
        String expectedOutput = "";

        //Act
        displayShowCommand.execute(new ArrayList<>(List.of("DISPLAY-SHOWS",movieTitle)));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
    }
}

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

import MovieBooking.entities.Movie;
import MovieBooking.services.MovieService;

@DisplayName("Testing DisplayMoviesCommand")
@ExtendWith(MockitoExtension.class)
public class DisplayMoviesCommandTest {
    
    @Mock
    MovieService movieServiceMock;

    @InjectMocks
    DisplayMoviesCommand displayMoviesCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Should print all the Movies and related information")
    public void execute_ShouldPrintAllMovies(){

        //Arrange
        List<Movie> movieList = new ArrayList<>(List.of(
            new Movie("1", "MovieA", 120),
            new Movie("2", "MovieB", 150),
            new Movie("3", "MovieC", 180)
        ));

        when(movieServiceMock.getMovieList()).thenReturn(movieList);
        String expectedOutput = "Movie Id - 1\n" + "Movie Name - MovieA\n" + "Movie Duration - 120\n" + "\n" + 
            "Movie Id - 2\n" + "Movie Name - MovieB\n" + "Movie Duration - 150\n" + "\n" + 
            "Movie Id - 3\n" + "Movie Name - MovieC\n" + "Movie Duration - 180";
        
        //Act
        displayMoviesCommand.execute(new ArrayList<>(List.of("DISPLAY-MOVIES")));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());

        verify(movieServiceMock).getMovieList();
    }
}

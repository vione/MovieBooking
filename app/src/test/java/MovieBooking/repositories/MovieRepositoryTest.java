package MovieBooking.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import MovieBooking.entities.Movie;

@DisplayName("Movie Repository Test")
public class MovieRepositoryTest {
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup(){
        Map<String,Movie> movieMap = new HashMap<>();
        movieMap.put("1", new Movie("1", "MovieA", 120));
        movieMap.put("2", new Movie("2", "MovieB", 110));
        movieMap.put("3", new Movie("3", "MovieC", 130));
        movieRepository = new MovieRepository(movieMap);
    }

    @Test
    @DisplayName("GetAllMovies Test should return the list of movies")
    public void getAllMovies_ShouldReturnAllMovies(){
        //Arrange
        List<Movie> expectedMovieList = new ArrayList<>(
            List.of(
                new Movie("1", "MovieA", 120),
                new Movie("2", "MovieB", 110),
                new Movie("3", "MovieC", 130)
            )
        );
        //Act
        List<Movie> originalMovieList = movieRepository.getAllMoveis();
        //Assert
        Assertions.assertTrue(expectedMovieList.size() == originalMovieList.size() &&
                                expectedMovieList.containsAll(originalMovieList) &&
                                originalMovieList.containsAll(expectedMovieList));  
    }

    @Test
    @DisplayName("getMovieById method Should Return Movie Given Movie Id")
    public void getMovieById_GivenMovieId_ShouldReturnMovie(){
        //Arrange
        Movie expectedMovie = new Movie("3","MovieC",130);
        //Act
       Movie actualMovie = movieRepository.getMovieById("3");
        //Assert
        Assertions.assertEquals(expectedMovie,actualMovie);
    }

    @Test
    @DisplayName("getMovieById  method Should Return null Given Movie Id If Movie not found")
    public void getMovieById_GivenMovieId_ShouldReturnNull(){
        //Arrange
        String movieId = "4";
        //Act
        Movie actualMovie = movieRepository.getMovieById(movieId);
        //Assert
        Assertions.assertNull(actualMovie);
    }

    @Test
    @DisplayName("saveMovie method Should Save Movie")
    public void saveMovie_ShouldSaveMovie(){
        //Arrange
        Movie expectedMovie = new Movie("4","MovieD",210);
        //Act
        movieRepository.saveMovie(expectedMovie);

        //Assert
        Assertions.assertEquals(expectedMovie, movieRepository.getMovieById("4"));
    }

}

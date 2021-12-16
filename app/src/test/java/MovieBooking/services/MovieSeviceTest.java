package MovieBooking.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import MovieBooking.entities.Movie;
import MovieBooking.repositories.MovieRepository;

@DisplayName("Testing the behaviour of movieService")
@ExtendWith(MockitoExtension.class)
public class MovieSeviceTest {
    
    @Mock
    MovieRepository movieRepositoryMock;

    @InjectMocks
    MovieService movieService;

    @Test
    @DisplayName("getAllMovies should return all movies")
    public void getAllMoviesTest(){
        //Arrange
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("1", "MovieA", 150));
        movieList.add(new Movie("2", "MovieB", 180));
        movieList.add(new Movie("3", "Moviec", 180));

        when(movieRepositoryMock.getAllMoveis()).thenReturn(movieList);

        //Act
        List<Movie> expectedList = movieService.getMovieList();

        //Assert
        Assertions.assertEquals(expectedList, movieList);
        verify(movieRepositoryMock).getAllMoveis();
    }

    @Test
    @DisplayName("getAllMovies should returnEmptyList if no movies")
    public void getAllMovies_ShouldReturnEmptyList(){
        //Arrange
        List<Movie> originalList = new ArrayList<>();
        when(movieRepositoryMock.getAllMoveis()).thenReturn(originalList);
        //Act
        List<Movie> expectedList = movieService.getMovieList();

        //Assert
        Assertions.assertEquals(expectedList, originalList);

        verify(movieRepositoryMock).getAllMoveis();
    }

}

package MovieBooking.repositories;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import MovieBooking.entities.Cinema;

@DisplayName("Cinema Repository Test")
public class CinemaRepositoryTest {

    private CinemaRepository cinemaRepository;
    @BeforeEach
    public void setup(){
        Map<String, Cinema> cinemaMap = new HashMap<>();
        cinemaMap.put("1", new Cinema("1", "CinemaA"));
        cinemaMap.put("2", new Cinema("2", "CinemaB"));
        cinemaMap.put("3", new Cinema("3", "CinemaC"));
        cinemaRepository = new CinemaRepository(cinemaMap);
    }

    @Test
    @DisplayName("getcinameById should return the cinema Given cinema Id")
    public void getCinemaById_GivenCinemaId_ShouldReturnCinema(){
        //Arrange
        Cinema expectedCinema = new Cinema("3", "CinemaC");
        //Act
        Cinema originalCinema = cinemaRepository.getCinemaById("3");
        //Asert
        Assertions.assertEquals(expectedCinema, originalCinema);
    }

    @Test
    @DisplayName("getCinemaById should return null Given Cinema Id when cinema not found")
    public void getCinemaById_GivenCinemaId_ShouldReturnNull(){
        //Arrage
        String cinemaId = "4";
        //Act
        Cinema originalCinema = cinemaRepository.getCinemaById(cinemaId);
        //Assert
        Assertions.assertNull(originalCinema);
    }

    @Test
    @DisplayName("saveCinema method should save cinema and should be finable")
    public void saveCinema_ShouldSaveCinema(){
        //Arrange
        Cinema expectedCinema = new Cinema("4", "CinemaD");
        //Act
        cinemaRepository.save(expectedCinema);
        //Assert
        Assertions.assertEquals(expectedCinema, cinemaRepository.getCinemaById("4"));
    }

    @Test
    @DisplayName("updateCinema method should update a cinema")
    public void updateCinema_ShouldUpdateCinema(){
        //Arrange
        Cinema expectedCinema = new Cinema("3", "CinemaD");
        //Act
        cinemaRepository.update(expectedCinema);
        //Assert
        Assertions.assertEquals(expectedCinema, cinemaRepository.getCinemaById("3"));
    }
    
}

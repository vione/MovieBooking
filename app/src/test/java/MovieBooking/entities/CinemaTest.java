package MovieBooking.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Cinema Test")
public class CinemaTest {

    @Test
    @DisplayName("getScreenByName should return Screen given ScreenName")
    public void getScreeByNameTest_ValidScreen(){
        //Arrange
        Cinema cinema = new Cinema("1", "CinemaA");
        Screen screen = new Screen("1", "ScreenA");
        cinema.addScreen(screen);
        cinema.addScreen(new Screen("2", "ScreenB"));
        cinema.addScreen(new Screen("3", "ScreenC"));
        Screen origionalScreen =  new Screen("2", "ScreenB");
        
        //Act
        Screen expectedScreen = cinema.getScreenByName("ScreenB");

        //Assert
        Assertions.assertEquals(expectedScreen, origionalScreen);
}
    @Test
    @DisplayName("getScreenByName Given Screen name shoudld return Null since screen is not present")
    public void getScreeByNameTest_InValidScreen() {
        //Arrange
        Cinema cinema = new Cinema("1", "CinemaA");
        Screen screen = new Screen("1", "ScreenA");
        cinema.addScreen(screen);
        cinema.addScreen(new Screen("2", "ScreenB"));
        cinema.addScreen(new Screen("3", "ScreenC"));

        //Act
        Screen expectedScreen = cinema.getScreenByName("ScreenD");

        //Assert
        Assertions.assertNull(expectedScreen);

    }


}

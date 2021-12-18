package MovieBooking.command;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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

import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.NoSuchTicketFoundException;
import MovieBooking.services.TicketService;

@DisplayName("Testing Cancel Ticekt Command")
@ExtendWith(MockitoExtension.class)
public class CancelTicketCommandTest {

    @Mock
    TicketService ticketServiceMock;

    @Mock
    Ticket ticket;

    @InjectMocks
    CancelTicketCommand cancelTicketCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    @DisplayName("execute function should delete the ticket")
    public void execute_ShouldReturnRemovalMessage() throws NoSuchTicketFoundException{

        //Arrange
        String ticketId = "1";
        String expectedString = "Ticket has been cancelled";

        //Act
        cancelTicketCommand.execute(new ArrayList<>(List.of("CANCEL-TICKET", ticketId)));

        //Assert
        Assertions.assertEquals(expectedString, outputStream.toString().trim());

        verify(ticketServiceMock).cancelTicket("1");
    }

    @Test
    @DisplayName("execute - Should print the no such ticket found exception")
    public void execute_ShouldReturnErrorMessage() throws NoSuchTicketFoundException{
        //Arrange
        String expectedString = "No such ticket found";
        doThrow(new NoSuchTicketFoundException()).when(ticketServiceMock).cancelTicket("1");        
        
        //Act
        cancelTicketCommand.execute(new ArrayList<>(List.of("CANCEL-TICKET", "1")));

        //Assert
        Assertions.assertEquals(expectedString, outputStream.toString().trim());
    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
    }

}

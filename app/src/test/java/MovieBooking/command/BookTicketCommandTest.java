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
import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.SeatNotAvailableException;
import MovieBooking.services.TicketService;

@DisplayName("Testing Book Display Command")
@ExtendWith(MockitoExtension.class)
public class BookTicketCommandTest {

    @Mock
    Ticket ticket;

    @Mock
    TicketService ticketServiceMock;

    @InjectMocks
    BookTicketCommand bookTicketCommand;
    

    //Setting up for Capturing output stream
    private final PrintStream standarOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Testing Book Ticket Method")
    public void bookTicket_shouldReturnTicketId() throws SeatNotAvailableException{
        //Arrange
        String customerId = "1";
        String showId = "1";
        Seat seat1_2 = new Seat("1#2", 1, 2);
        Seat seat1_3 = new Seat("1#3", 1, 3);
        List<Seat> seatList = new ArrayList<>(List.of(seat1_2, seat1_3));
        String expectedOutput = "TicketId - 1";

        when(ticketServiceMock.bookTicket(customerId, showId, seatList)).thenReturn(ticket);
        when(ticket.getId()).thenReturn("1");

        //Act
        bookTicketCommand.execute(new ArrayList<>(List.of("BOOK-TICKET",
            customerId,showId,seat1_2.getId(),seat1_3.getId())));
        
        //Assert
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());

        verify(ticketServiceMock).bookTicket(customerId, showId, seatList);

    }

    @Test
    @DisplayName("execute Method should print error message")
    public void execute_ShoudPrintErrorMessage() throws SeatNotAvailableException{
        //Arrange
        String customerId = "1";
        String showId = "1";
        Seat seat1_2 = new Seat("1#2", 1, 2);
        Seat seat1_3 = new Seat("1#3", 1, 3);
        List<Seat> seatList = new ArrayList<>(List.of(seat1_2, seat1_3));
        String exceptedOutput = "Some Seats have been booked! Please try booking available seats";
        when(ticketServiceMock.bookTicket(customerId, showId, seatList)).thenThrow(new SeatNotAvailableException());

        //Act
        bookTicketCommand.execute(new ArrayList<>(List.of("BOOK-TICKET",
        customerId,showId,seat1_2.getId(),seat1_3.getId())));

        //Assertions
        Assertions.assertEquals(exceptedOutput,outputStream.toString().trim());

        verify(ticketServiceMock).bookTicket(customerId, showId, seatList);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standarOut);
    }

}

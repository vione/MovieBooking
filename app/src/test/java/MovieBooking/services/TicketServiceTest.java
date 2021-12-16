package MovieBooking.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import MovieBooking.entities.Cinema;
import MovieBooking.entities.Customer;
import MovieBooking.entities.Movie;
import MovieBooking.entities.Screen;
import MovieBooking.entities.Seat;
import MovieBooking.entities.Show;
import MovieBooking.entities.ShowSeat;
import MovieBooking.entities.Ticket;
import MovieBooking.exceptions.NoSuchTicketFoundException;
import MovieBooking.exceptions.SeatNotAvailableException;
import MovieBooking.repositories.CustomerRepository;
import MovieBooking.repositories.ShowRepository;
import MovieBooking.repositories.ShowSeatRepository;
import MovieBooking.repositories.TicketRepository;

@DisplayName("Testing Ticeket Services")
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    private Customer customer;
    private Show show;
    private ShowSeat showSeat1_1_2;
    private ShowSeat showSeat1_1_3;
    private Seat seat1_2;
    private Seat seat1_3;

    @Mock
    TicketRepository ticketRepositoryMock;

    @Mock
    ShowRepository showRepositoryMock;

    @Mock
    ShowSeatRepository showSeatRepositoryMock;

    @Mock
    CustomerRepository customerRepositoryMock;

    @InjectMocks
    TicketService ticketService;

    @BeforeEach
    void setup() throws ParseException{
        customer = new Customer("1","CustomerA","customerA@gmail.com");
        show  = new Show("1",
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 13:30"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/07/2021 16:00"),
                new Screen("1","ScreenA"),
                new Cinema("1","CinemaA"),
                new Movie("1","MovieA",120));
        showSeat1_1_2 = new ShowSeat("1#1#2",new Seat("1#2",1,2),show);
        showSeat1_1_3 = new ShowSeat("1#1#3",new Seat("1#3",1,3),show);
        seat1_2 = new Seat("1#2",1,2);
        seat1_3 = new Seat("1#3",1,3);
    }

    @Test
    @DisplayName("bookTicket method should book and return the ticket")
    public void bookTicketTest_shouldBookAndReturnTicket() throws SeatNotAvailableException { 
        //Arrange
        List<Seat> seatList = new ArrayList<>( List.of(seat1_2, seat1_3));
        Ticket actualTicket = new Ticket("1", customer, show, seatList);
        when(customerRepositoryMock.getCustomerByID("1")).thenReturn(customer);
        when(showRepositoryMock.getShowById("1")).thenReturn(show);
        when(showSeatRepositoryMock.getShowSeat(anyString(), anyString())).thenReturn(showSeat1_1_2,showSeat1_1_3,
            showSeat1_1_2, showSeat1_1_3);
        when(ticketRepositoryMock.saveTicket(customer, show, seatList)).thenReturn(actualTicket);


        //Act
        Ticket expectedTicket = ticketService.bookTicket(customer.getId(), show.getId(),seatList);

        //Assert
        Assertions.assertEquals(expectedTicket, actualTicket);
    }

    @Test
    @DisplayName("bookTicket method should Throw Exception If Seat is Already Booked")
    public void bookTicket_ShouldThrowExceptionIfSeatAlreadyBooked() throws SeatNotAvailableException {
        //Arrange
        showSeat1_1_2.lock();

        List<Seat> seatList = new ArrayList<>(
                List.of(seat1_2, seat1_3)
        );

        when(customerRepositoryMock.getCustomerByID("1")).thenReturn(customer);
        when(showRepositoryMock.getShowById("1")).thenReturn(show);
        when(showSeatRepositoryMock.getShowSeat(anyString(),anyString())).thenReturn(showSeat1_1_2,showSeat1_1_3,showSeat1_1_2,showSeat1_1_3);

        //Act and Assert
        Assertions.assertThrows(SeatNotAvailableException.class,() -> ticketService.bookTicket("1","1",seatList));

    }

    @Test
    @DisplayName("cancelTicket method should Cancel Ticket Given Ticket Id")
    public void cancelTicket_GivenTicketId_ShouldCancelTicket() throws NoSuchTicketFoundException {
        //Arrange
        showSeat1_1_2.lock();
        showSeat1_1_3.lock();

        List<Seat> seatList = new ArrayList<>(
                List.of(seat1_2, seat1_3)
        );

        Ticket ticket = new Ticket("1",customer,show,seatList);

        when(ticketRepositoryMock.getTicketById("1")).thenReturn(ticket);
        when(showSeatRepositoryMock.getShowSeat("1","1#2")).thenReturn(showSeat1_1_2);
        when(showSeatRepositoryMock.getShowSeat("1","1#3")).thenReturn(showSeat1_1_3);

        //Act
        ticketService.cancelTicket("1");

        //Assert
        showSeat1_1_2.unlock();
        verify(showSeatRepositoryMock).updateShowSeat(showSeat1_1_2);
        showSeat1_1_3.unlock();
        verify(showSeatRepositoryMock).updateShowSeat(showSeat1_1_3);
        verify(ticketRepositoryMock).removeTicket("1");

    }

    @Test
    @DisplayName("cancelTicket method should Throw Exception If No Ticket Found Given Ticket Id")
    public void cancelTicket_GivenTicketId_ShouldShouldThrowExceptionIfNoTicketFound() throws NoSuchTicketFoundException {
        //Arrange
        when(ticketRepositoryMock.getTicketById("1")).thenReturn(null);

        //Act and Assert
        Assertions.assertThrows(NoSuchTicketFoundException.class,()->ticketService.cancelTicket("1"));

        verify(ticketRepositoryMock).getTicketById("1");

    }



    
}

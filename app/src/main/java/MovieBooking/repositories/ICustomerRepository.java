package MovieBooking.repositories;

import MovieBooking.entities.Customer;

public interface ICustomerRepository {
    public Customer getCustomerByID(String id);
    public void saveCustomer(Customer customer);
}

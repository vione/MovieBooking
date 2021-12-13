package MovieBooking.repositories;

import java.util.HashMap;
import java.util.Map;

import MovieBooking.entities.Customer;

public class CustomerRepository implements ICustomerRepository {

    private final Map<String, Customer> customerMap;

    public CustomerRepository(){
        customerMap = new HashMap<>();
    }

    public CustomerRepository(Map<String, Customer> map){
        customerMap = map;
    }

    @Override
    public void saveCustomer(Customer customer){
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public Customer getCustomerByID(String Id){
        return customerMap.get(Id);
    }
    
}

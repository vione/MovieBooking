package MovieBooking.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import MovieBooking.entities.Customer;
import MovieBooking.repositories.ICustomerRepository;

public class CustomerData implements Idata{
    
    private final ICustomerRepository iCustomerRepository;

    public CustomerData(ICustomerRepository iCustomerRepository) {
        this.iCustomerRepository = iCustomerRepository;
    }

    @Override
    public void loadData(String datapath, String delimeter) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(datapath));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(delimeter));
                addCustomer(tokens.get(0), tokens.get(1), tokens.get(2));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    private void addCustomer(String id, String customerName, String email){
        iCustomerRepository.saveCustomer(new Customer(id, customerName, email));
    }

    
}

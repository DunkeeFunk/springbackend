package mike.springframework.springmvcrest.services;

import mike.springframework.springmvcrest.domain.Customer;
import mike.springframework.springmvcrest.domain.CustomerData;
import mike.springframework.springmvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    // Standard constructor
    public CustomerServiceImpl(CustomerRepository customerRepository){

        this.customerRepository = customerRepository;
    }
    // Override the methods in the interface
    @Override
    public Customer findCustomerById(Long id) {
        // use .get incase this returns a null
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAllCustomers() {
        // standard find all, if nothing in the Hib DB then just return an
        // empty json array
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer){
        // save customer and return saved customer in the json
        return customerRepository.save(customer);
    }

    @Override
    public Customer editCustomer(Customer newCustomer, Long id) {
        // pass this a customer obj and an id, if that cust does not exisit
        // create it and save it
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstname( newCustomer.getFirstname());
                    customer.setLastname( newCustomer.getLastname());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                newCustomer.setId(id);
                return customerRepository.save(newCustomer);
                });
    }


    @Override
    public void deleteCustomer(Long id) {
        // delete a customer by its id and return HTTP okay as seen in
        // check the controller for the HTTP ok
        customerRepository.deleteById(id);
    }
}

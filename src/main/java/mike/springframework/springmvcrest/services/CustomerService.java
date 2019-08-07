package mike.springframework.springmvcrest.services;

import mike.springframework.springmvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer editCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);

}

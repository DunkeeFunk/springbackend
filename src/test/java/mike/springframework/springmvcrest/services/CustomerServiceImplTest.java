package mike.springframework.springmvcrest.services;

import mike.springframework.springmvcrest.domain.Customer;
import mike.springframework.springmvcrest.repositories.CustomerDataRepository;
import mike.springframework.springmvcrest.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    //////////////////// Tests ///////////////////////////
    @Test
    public void findCustomerById() {
        // find cust by id test
        Customer exp_customer = new Customer();
        exp_customer.setId(1L);
        exp_customer.setFirstname("Michael");
        exp_customer.setLastname("Weston");
        // when custrepo.findbyid is called with a value of 1L then make sure you return me
        // my expected customer, beware of the optional type here.
        when(customerRepository.findById(1L)).thenReturn(Optional.of(exp_customer));
        // new call this function the way it is called in the codebase for the actual result
        Customer customer_acc = customerServiceImpl.findCustomerById(1L);
        // these both should be equal, if not then theres something wrong.
        assertEquals(exp_customer, customer_acc);
    }

    @Test
    public void findAllCustomers() {
        // customer one
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michael");
        customer1.setLastname("Weston");
        // customer two
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");
        // make list to put them in
        List custList = new ArrayList<Customer>();
        custList.add(customer1);
        custList.add(customer2);
        // Again mocking the way this is called in the code but give me my new cust list regardless
        when(customerRepository.findAll()).thenReturn(custList);
        // no do the actual call the way I have it done in code
        List ex_cust_list = customerServiceImpl.findAllCustomers();
        // these two should be equal or there is something wrong
        assertEquals(custList, ex_cust_list);
    }

    @Test
    public void saveCustomer() {
        // same principal s apply here as above
        Customer exp_customer = new Customer();
        exp_customer.setId(1L);
        exp_customer.setFirstname("Michael");
        exp_customer.setLastname("Weston");

        when(customerRepository.save(exp_customer)).thenReturn(exp_customer);

        Customer acc_customer = customerServiceImpl.saveCustomer(exp_customer);

        assertEquals(acc_customer, exp_customer);
    }

    @Test
    public void editCustomer() {
        Customer custBefore = new Customer();
        custBefore.setId(1L);
        custBefore.setFirstname("Michael");
        custBefore.setLastname("Weston");

        Customer custAfter = new Customer();
        custAfter.setId(2L);
        custAfter.setFirstname("Michael");
        custAfter.setLastname("Weston");

        when(customerRepository.findById(1L).map(customer -> {
            customer.setFirstname(custAfter.getFirstname());
            customer.setLastname(custAfter.getLastname());
            return customerRepository.save(customer);
        })
        .orElseGet(() -> {
            custAfter.setId(2L);
            return customerRepository.save(custAfter);
        })).thenReturn(custAfter);

        Customer ex_customer = customerServiceImpl.editCustomer(custBefore, 2L);

        assertEquals(custAfter, ex_customer);
    }

    @Test
    public void deleteCustomer() {
        // this function calls customerRepository.deleteById(1L)
        customerServiceImpl.deleteCustomer(1L);
        // verify verifies that deleteById was called under the hood. this is a void method
        // so all we can do is verify the call
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
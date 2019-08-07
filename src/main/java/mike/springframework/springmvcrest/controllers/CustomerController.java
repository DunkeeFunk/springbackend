package mike.springframework.springmvcrest.controllers;

import mike.springframework.springmvcrest.domain.Customer;
import mike.springframework.springmvcrest.domain.CustomerData;
import mike.springframework.springmvcrest.domain.JwtUser;
import mike.springframework.springmvcrest.security.JwtGenerator;
import mike.springframework.springmvcrest.services.CustomerDataService;
import mike.springframework.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/rest/v1/customers";

    private final CustomerService customerService;
    private final CustomerDataService customerDataService;
    private JwtGenerator jwtGenerator;

    public CustomerController(CustomerService customerService, CustomerDataService customerDataService){
        this.customerService = customerService;
        this.customerDataService = customerDataService;
    }

    @GetMapping
    List<Customer> getAllCustomers(){
        System.out.println("got message log");
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        System.out.println("got customer by id");
        return customerService.findCustomerById(id);
    }

    @GetMapping("/custdata/{id}")
    public CustomerData getCustomerData(@PathVariable Long id){
        System.out.println("got customer data by id");
        return customerDataService.findCustomerDataById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer customer){
        System.out.println("New customer created log");
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/editcust/{id}")
    public Customer editCustomer(@RequestBody Customer customer, @PathVariable Long id){
        return customerService.editCustomer(customer, id);
    }

    @DeleteMapping("/custdel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}

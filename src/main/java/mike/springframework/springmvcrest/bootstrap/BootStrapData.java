package mike.springframework.springmvcrest.bootstrap;

import mike.springframework.springmvcrest.domain.Customer;
import mike.springframework.springmvcrest.domain.CustomerData;
import mike.springframework.springmvcrest.repositories.CustomerDataRepository;
import mike.springframework.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CustomerDataRepository customerDataRepository;

    public BootStrapData(CustomerRepository customerRepository, CustomerDataRepository customerDataRepository){

        this.customerRepository = customerRepository;
        this.customerDataRepository = customerDataRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Saving data in the db before load
        System.out.println("Loading Customer Data");

        Customer c1 = new Customer();
        c1.setFirstname("Michael");
        c1.setLastname("Weston");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c2.setFirstname("Sam");
        c2.setLastname("Axe");
        customerRepository.save(c2);

        Customer c3 = new Customer();
        c3.setFirstname("Fiona");
        c3.setLastname("Glenann");
        customerRepository.save(c3);

        System.out.println("Customers Saved " + customerRepository.count());

        System.out.println("Generating Customer Data");
        // beware only one customer data obj has been saved in correspondence to
        // an actual customer
        CustomerData cd1 = new CustomerData();
        cd1.setId(1L);
        cd1.setData(this.randNums());
        cd1.setDates(this.dates());
        customerDataRepository.save(cd1);
        // sys.out comes out on console when you run - a logger would be nice
        // I can change verbosity of the logger that comes with Spring so maybe this is an option
        System.out.println("Generated Customer Data");
    }

    public List<Double> randNums(){
        List<Double> data;
        Random rand = new Random();
        return rand.doubles()
                .distinct()
                .map(d -> 1 + d * 9)
                .limit(100)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<LocalDate> dates(){
        // example dates
        LocalDate startDate = LocalDate.of(2019, 07, 15);
        LocalDate endDate = LocalDate.of(2019, 10, 23);
        // get the limit between the two dates, this could be a lot cleaner
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        // return a list of the time stamps between these dates
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}

package mike.springframework.springmvcrest.repositories;

import mike.springframework.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

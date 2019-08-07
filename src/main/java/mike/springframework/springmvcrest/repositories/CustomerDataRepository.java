package mike.springframework.springmvcrest.repositories;

import mike.springframework.springmvcrest.domain.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataRepository extends JpaRepository<CustomerData, Long> {

}

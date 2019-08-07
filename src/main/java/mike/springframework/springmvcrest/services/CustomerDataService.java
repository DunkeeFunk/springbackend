package mike.springframework.springmvcrest.services;

import mike.springframework.springmvcrest.domain.CustomerData;

public interface CustomerDataService {

    CustomerData findCustomerDataById(Long id);

}

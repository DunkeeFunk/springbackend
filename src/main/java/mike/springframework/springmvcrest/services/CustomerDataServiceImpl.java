package mike.springframework.springmvcrest.services;

import mike.springframework.springmvcrest.domain.CustomerData;
import mike.springframework.springmvcrest.repositories.CustomerDataRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    private final CustomerDataRepository customerDataRepository;

    public CustomerDataServiceImpl(CustomerDataRepository customerDataRepository){
        this.customerDataRepository = customerDataRepository;
    }

    @Override
    public CustomerData findCustomerDataById(Long id){
        return customerDataRepository.findById(id).get();
    }

}

package api.processingcustomerdata.service.impl;


import api.processingcustomerdata.dto.CustomerInputDto;
import api.processingcustomerdata.dto.CustomerOutputDto;
import api.processingcustomerdata.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private List<CustomerOutputDto> customers = new ArrayList<>();

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.getAllCustomers();
        return DataTransformationUtils.transformInputData(customers);
    }

    public Customer getCustomerById(int id) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer != null) {
            return DataTransformationUtils.transformInputData(List.of(customer)).get(0);
        }
        return null;
    }


}

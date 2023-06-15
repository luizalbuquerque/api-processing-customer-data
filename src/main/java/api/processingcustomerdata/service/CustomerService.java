package api.processingcustomerdata.service;

import api.processingcustomerdata.model.Customer;
import api.processingcustomerdata.service.impl.CustomerServiceImpl;

import java.io.IOException;
import java.util.List;

public interface CustomerService{


    List<Customer> getEligibleCustomers(String region, String classification) throws IOException;
}

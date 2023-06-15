package api.processingcustomerdata.controller;

import api.processingcustomerdata.model.Customer;
import api.processingcustomerdata.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{region}/{classification}")
    public List<Customer> getEligibleCustomers(@PathVariable String region, @PathVariable String classification) throws IOException {
        return customerService.getEligibleCustomers(region, classification);
    }
}

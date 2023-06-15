package api.processingcustomerdata.controller;

import api.processingcustomerdata.dto.CustomerOutputDto;
import api.processingcustomerdata.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEligibleCustomers(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<CustomerOutputDto> customers = customerService.getEligibleCustomers(pageNumber, pageSize);
        int totalCount = customerService.getTotalCount();

        Map<String, Object> response = new HashMap<>();
        response.put("pageNumber", pageNumber);
        response.put("pageSize", pageSize);
        response.put("totalCount", totalCount);
        response.put("users", customers);

        return ResponseEntity.ok(response);
    }
}

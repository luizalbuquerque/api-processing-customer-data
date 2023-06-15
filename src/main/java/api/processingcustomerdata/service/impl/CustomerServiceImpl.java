package api.processingcustomerdata.service.impl;

import api.processingcustomerdata.model.Customer;
import api.processingcustomerdata.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final String CSV_FILE_PATH = "path/to/inputbackend.csv";
    private final String JSON_FILE_PATH = "path/to/inputbackend.json";

    public List<Customer> getEligibleCustomers(String region, String classification) throws IOException {
        List<Customer> customers = loadCustomers();
        List<Customer> eligibleCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (isCustomerEligible(customer, region, classification)) {
                eligibleCustomers.add(customer);
            }
        }

        return eligibleCustomers;
    }

    private List<Customer> loadCustomers() throws IOException {
        List<Customer> customers = new ArrayList<>();

        // Load CSV customers
        CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH));
        String[] nextLine;
        ObjectReader csvObjectReader = new ObjectMapper().readerFor(Customer.class);

        while ((nextLine = csvReader.readNext()) != null) {
            Customer customer = csvObjectReader.readValue(nextLine);
            customers.add(customer);
        }

        csvReader.close();

        // Load JSON customers
        ObjectReader jsonObjectReader = new ObjectMapper().readerFor(Customer.class);
        FileReader jsonFileReader = new FileReader(JSON_FILE_PATH);
        Customer[] jsonCustomers = jsonObjectReader.readValue(jsonFileReader);

        for (Customer customer : jsonCustomers) {
            customers.add(customer);
        }

        jsonFileReader.close();

        return customers;
    }

    private boolean isCustomerEligible(Customer customer, String region, String classification) {
        // Implemente a lógica de classificação de acordo com as regras de negócio
        // Use as informações do objeto Customer para determinar se o cliente é elegível
        // com base na região e na classificação fornecida
        // Retorne true se o cliente for elegível, caso contrário, retorne false
    }

}

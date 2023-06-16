package api.processingcustomerdata.service.impl;

import api.processingcustomerdata.model.Customer;
import api.processingcustomerdata.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private String CSV_FILE_PATH = "C:\\Users\\Albuquerque\\Documents\\WorkSpace_2023\\api-processing-customer-data\\src\\main\\resources\\inputbackend.csv";
    private String JSON_FILE_PATH = "C:\\Users\\Albuquerque\\Documents\\WorkSpace_2023\\api-processing-customer-data\\src\\main\\resources\\inputbackend.json";

    private void downloadFiles() {
        try {
            String csvFileName = "C:\\Users\\Albuquerque\\Documents\\WorkSpace_2023\\api-processing-customer-data\\src\\main\\resources\\inputbackend.csv";
            String jsonFileName = "C:\\Users\\Albuquerque\\Documents\\WorkSpace_2023\\api-processing-customer-data\\src\\main\\resources\\inputbackend.json";

            // Fazer o download do arquivo CSV
            FileUtils.copyURLToFile(new URL("https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.csv"), new File(csvFileName));
            CSV_FILE_PATH = new File(csvFileName).getAbsolutePath();

            // Fazer o download do arquivo JSON
            FileUtils.copyURLToFile(new URL("https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.json"), new File(jsonFileName));
            JSON_FILE_PATH = new File(jsonFileName).getAbsolutePath();

            System.out.println("Arquivos baixados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao baixar os arquivos: " + e.getMessage());
        }
    }

    public List<Customer> getEligibleCustomers(String region, String classification) throws IOException, CsvValidationException {
        downloadFiles();

        List<Customer> customers = loadCustomers();
        List<Customer> eligibleCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (isCustomerEligible(customer, region, classification)) {
                eligibleCustomers.add(customer);
            }
        }

        return eligibleCustomers;
    }

    private List<Customer> loadCustomers() throws IOException, CsvValidationException {
        List<Customer> customers = new ArrayList<>();

        // Load CSV customers
        CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH));
        String[] nextLine;
        ObjectReader csvObjectReader = new ObjectMapper().readerFor(Customer.class);

        // Skip the header line
        csvReader.readNext();

        while ((nextLine = csvReader.readNext()) != null) {
            String csvLine = String.join(",", nextLine);
            try {
                Customer customer = csvObjectReader.readValue(csvLine);
                customers.add(customer);
            } catch (JsonProcessingException e) {
                System.out.println("Error processing CSV line: " + csvLine);
                e.printStackTrace();
            }
        }

        csvReader.close();

        // Load JSON customers
        ObjectReader jsonObjectReader = new ObjectMapper().readerFor(Customer.class);
        FileReader jsonFileReader = new FileReader(JSON_FILE_PATH);
        Customer jsonCustomer = jsonObjectReader.readValue(jsonFileReader);

        customers.add(jsonCustomer);

        jsonFileReader.close();


        return customers;
    }



    private boolean isCustomerEligible(Customer customer, String region, String classification) {
        // Implemente a lógica de classificação de acordo com as regras de negócio
        // Use as informações do objeto Customer para determinar se o cliente é elegível
        // com base na região e na classificação fornecida
        // Retorne true se o cliente for elegível, caso contrário, retorne false
        return false;
    }
}

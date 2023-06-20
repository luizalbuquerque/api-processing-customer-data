package api.processingcustomerdata.service.impl;

import api.processingcustomerdata.model.CustomCustomerDeserializer;
import api.processingcustomerdata.model.Customer;
import api.processingcustomerdata.model.Location;
import api.processingcustomerdata.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

        // Skip the header line
        csvReader.readNext();

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            String csvLine = String.join(",", nextLine);

            // Convert the "gender" field to a JSON array
            String[] fields = csvLine.split(",");
            String[] genderField = new String[]{fields[0]};
            fields[0] = new ObjectMapper().writeValueAsString(genderField);
            String jsonLine = String.join(",", fields);

            // Parse the JSON line using the custom deserializer
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Customer.class, new CustomCustomerDeserializer());
            objectMapper.registerModule(module);

            Customer customer = objectMapper.readValue(jsonLine, Customer.class);
            customers.add(customer);
        }

        csvReader.close();


        // Load JSON customers
        ObjectReader jsonObjectReader = new ObjectMapper().readerFor(Customer.class);
        FileReader jsonFileReader = new FileReader(JSON_FILE_PATH);

        // Read JSON as a single customer object
        Customer jsonCustomer = jsonObjectReader.readValue(jsonFileReader);
        customers.add(jsonCustomer);

        jsonFileReader.close();


        return customers;
    }


    private boolean isCustomerEligible(Customer customer, String region, String classification) {

        // Verificar se a região do cliente corresponde à região fornecida
        Location location = customer.getLocation();
        if (location == null || !location.getRegion().equalsIgnoreCase(region)) {
            return false;
        }

        // Verificar se a classificação do cliente corresponde à classificação fornecida
        String classificationCustomer = customer.getClassification();
        if (classificationCustomer == null || !classificationCustomer.equalsIgnoreCase(classification)) {
            return false;
        }

        // Se o cliente atender a ambas as condições, ele é elegível
        return true;
    }
}
package api.processingcustomerdata.service.impl;

import api.processingcustomerdata.model.*;
import api.processingcustomerdata.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

        List<Customer> customersList = loadCustomers();
        List<Customer> eligibleCustomers = new ArrayList<>();

        for (Customer customer : customersList) {
            if (isCustomerEligible(customer, region, classification)) {
                eligibleCustomers.add(customer);
            }
        }

        return eligibleCustomers;
    }

    private List<Customer> loadCustomers() throws IOException, CsvValidationException {

        List<Customer> customersListOfLoadCustomers = new ArrayList<>();

        // Add all CSV customersListOfLoadCustomers to our customer list
        customersListOfLoadCustomers.addAll(loadCsvCustomers());

        // Add all Json customersListOfLoadCustomers to our customer list
        customersListOfLoadCustomers.addAll(loadJsonCustomers());

        return customersListOfLoadCustomers;
    }

    private List<Customer> loadCsvCustomers() throws IOException, CsvValidationException {
        List<Customer> csvCustomers = new ArrayList<>();

        // Load CSV customers
        CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH));

        // Skip the header line
        csvReader.readNext();

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            String csvLine = String.join(",", nextLine);
            Customer customer = parseCsvLine(csvLine);
            csvCustomers.add(customer);
        }

        csvReader.close();

        return csvCustomers;
    }

    private Customer parseCsvLine(String csvLine) throws IOException {
        String[] fields = csvLine.split(",");

        Customer customer = new Customer();
        customer.setType(fields[0]);
        customer.setGender(fields[1]);

        Name name = new Name();
        name.setTitle(fields[2]);
        name.setFirst(fields[3]);
        name.setLast(fields[4]);
        customer.setName(name);

        Location location = new Location();
        location.setStreet(fields[5]);
        location.setCity(fields[6]);
        location.setState(fields[7]);
        location.setCountry(fields[8]);
        location.setPostcode(fields[9]);
        location.setTimezone(fields[10]);
        location.setRegion(fields[11]);
        location.setCoordinates(fields[12]);
        customer.setLocation(location);

        customer.setEmail(fields[13]);
        customer.setBirthday(fields[14]);
        customer.setRegistered(fields[15]);

        List<String> mobileNumbers = new ArrayList<>();
        mobileNumbers.add(fields[16]);
        // Adicione mais números de celular, se necessário
        customer.setMobileNumbers(mobileNumbers);

        Picture picture = new Picture();
        picture.setLarge(fields[17]);
        picture.setMedium(fields[18]);
        picture.setThumbnail(fields[19]);
        customer.setPicture(picture);

        customer.setNationality(fields[20]);
        customer.setClassification(fields[21]);

        // Armazene todos os campos para referência futura, se necessário
        customer.setFields(fields);

        return customer;
    }


    private List<Customer> loadJsonCustomers() throws IOException {
        List<Customer> jsonCustomersList = new ArrayList<>();

        // Load JSON customers
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new File(JSON_FILE_PATH);

        if (jsonFile.exists()) {
            JsonNode rootNode = jsonMapper.readTree(jsonFile);

            if (rootNode.isArray()) {
                for (JsonNode jsonNode : rootNode) {
                    try {
                        Customer customer = jsonMapper.treeToValue(jsonNode, Customer.class);
                        jsonCustomersList.add(customer);
                    } catch (JsonProcessingException e) {
                        // Handle the exception as per your requirements
                        System.err.println("Error processing JSON node: " + e.getMessage());
                    }
                }
            } else {
                try {
                    Customer customer = jsonMapper.readValue(jsonFile, Customer.class);
                    jsonCustomersList.add(customer);
                } catch (JsonProcessingException e) {
                    // Handle the exception as per your requirements
                    System.err.println("Error processing JSON file: " + e.getMessage());

                    Customer customer = new Customer();
//                    customer.setName(rootNode.get("name").get("first").asText());
                    customer.setEmail(rootNode.get("email").asText());
                    // Preencha os demais atributos do Customer conforme necessário
                    jsonCustomersList.add(customer);
                }
            }
        }

        return jsonCustomersList;
    }




    private boolean isCustomerEligible(Customer customer, String region, String classification) {
        Location location = customer.getLocation();
        String classificationCustomer = customer.getClassification();

//        if (location == null || !location.getRegion().equalsIgnoreCase(region)) {
//            return false;
//        }
//
//        return classificationCustomer != null && classificationCustomer.equalsIgnoreCase(classification);
//    }
        return true;
    }
}

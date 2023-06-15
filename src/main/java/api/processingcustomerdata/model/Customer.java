package api.processingcustomerdata.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String type;
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private String birthday;
    private String registered;
    private List<String> telephoneNumbers;
    private List<String> mobileNumbers;
    private Picture picture;
    private String nationality;
}

package api.processingcustomerdata.dto;

import lombok.Data;

@Data
public class CustomerOutput {

    private String type;
    private String gender;
    private Name name;
    private LocationOutput location;
    private String email;
    private String birthday;
    private String registered;
    private List<String> telephoneNumbers;
    private List<String> mobileNumbers;
    private Picture picture;
    private String nationality;
}

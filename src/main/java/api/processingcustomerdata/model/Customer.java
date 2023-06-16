package api.processingcustomerdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private List<Result> results;
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
    private String classification;
}

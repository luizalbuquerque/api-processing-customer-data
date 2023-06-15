package api.processingcustomerdata.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerOutputDto {

    private String type;
    private String gender;
    private NameDto name;
    private LocationOutputDto location;
    private String email;
    private String birthday;
    private String registered;
    private List<String> telephoneNumbers;
    private List<String> mobileNumbers;
    private PictureDto picture;
    private String nationality;
}

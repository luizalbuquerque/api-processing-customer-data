package api.processingcustomerdata.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerInputDto {

    private String gender;
    private NameDto name;
    private LocationDto location;
    private String email;
    //private Dob dob;
    private Date registeredDate;
    private String phone;
    private String cell;
    private PictureDto picture;
}

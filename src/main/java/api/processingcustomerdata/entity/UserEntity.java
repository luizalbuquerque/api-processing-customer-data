package api.processingcustomerdata.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserEntity {
    private String gender;
    private NameEntity name;
    private LocationEntitiy location;
    private String email;
    private String birthday;
    private String registered;
    private List<String> telephoneNumbers;
    private List<String> mobileNumbers;
    private PictureEntity picture;
    private String nationality;
    // getters e setters
}

package api.processingcustomerdata.dto;


import lombok.Data;

@Data
public class LocationOutputDto {

    private String region;
    private String street;
    private String city;
    private String state;
    private String postcode;
    private CoordinatesDto coordinates;
    private TimezoneDto timezone;
}

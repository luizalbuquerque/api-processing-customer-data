package api.processingcustomerdata.entity;

import lombok.Data;

@Data
public class LocationEntitiy{
    private String region;
    private String street;
    private String city;
    private String state;
    private int postcode;
    private Coordinates coordinates;
    private TimezoneEntity timezone;
}
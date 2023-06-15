package api.processingcustomerdata.model;

import lombok.Data;

@Data
public class Location {

    private String region;
    private String street;
    private String city;
    private String state;
    private int postcode;
    private Coordinates coordinates;
    private Timezone timezone;
}

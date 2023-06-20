package api.processingcustomerdata.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CustomCustomerDeserializer extends StdDeserializer<Customer> {

    public CustomCustomerDeserializer() {
        this(null);
    }

    public CustomCustomerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Customer deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String[] fields = parser.readValueAs(String[].class);
        String gender = fields[0];
        String[] remainingFields = new String[fields.length - 1];
        System.arraycopy(fields, 1, remainingFields, 0, remainingFields.length);

        // Create a new Customer instance with the remaining fields
        Customer customer = new Customer();
        customer.setGender(gender);
        customer.setFields(remainingFields);

        return customer;
    }
}

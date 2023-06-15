package api.processingcustomerdata;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "API Processing Customer Data", version = "1.0.0"))
@SpringBootApplication
public class ProcessingCustomerDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessingCustomerDataApplication.class, args);
	}

}

package api.processingcustomerdata;
import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String classification;
    private String geographicLocation;
    // outros atributos adicionaveis

    // Constructors

    public Client() {
        // Empty constructor for Spring Data JPA
    }

    public Client(String name, String address, String phoneNumber, String classification, String geographicLocation) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classification = classification;
        this.geographicLocation = geographicLocation;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    // Outros métodos e lógica de negócio

    // Exemplo de método para formatar o número de telefone para o formato E.164
//    public String formatPhoneNumberToE164() {
//        // Implemente a lógica para formatar o número de telefone no formato E.164
//        // ...
//        return formattedPhoneNumber;
    }


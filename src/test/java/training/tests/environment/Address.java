package training.tests.environment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    public String getCity() {
        return city;
    }

    public String getSuite() {
        return suite;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package example.gettingstarted.demo6;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


public class Address {

    private String street;
    private String city;

    @XmlInverseReference(mappedBy = "address")
    private Customer customer;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

package example.gettingstarted.demo7;

import javax.xml.bind.annotation.XmlIDREF;

public class Address {

    private String street;
    private String city;
    @XmlIDREF
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

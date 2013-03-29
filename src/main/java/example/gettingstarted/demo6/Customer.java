package example.gettingstarted.demo6;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlID;


public class Customer {

    @XmlID
    private String name;
    private Address address;
    private List<PhoneNumber> phoneNumbers;

    public Customer() {
        phoneNumbers = new ArrayList<PhoneNumber>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
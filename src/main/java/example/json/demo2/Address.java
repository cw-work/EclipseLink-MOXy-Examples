package example.json.demo2;

import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlType(propOrder = { "country", "state", "city", "street", "postalCode" })
public class Address implements IAddress {

    @XmlPath("Placemark/ns:AddressDetails/ns:Country/ns:AdministrativeArea/ns:Locality/ns:Thoroughfare/ns:ThoroughfareName/text()")
    private String street;
    @XmlPath("Placemark/ns:AddressDetails/ns:Country/ns:AdministrativeArea/ns:Locality/ns:LocalityName/text()")
    private String city;
    @XmlPath("Placemark/ns:AddressDetails/ns:Country/ns:AdministrativeArea/ns:AdministrativeAreaName/text()")
    private String state;
    @XmlPath("Placemark/ns:AddressDetails/ns:Country/ns:CountryNameCode/text()")
    private String country;
    @XmlPath("Placemark/ns:AddressDetails/ns:Country/ns:AdministrativeArea/ns:Locality/ns:PostalCode/ns:PostalCodeNumber/text()")
    private String postalCode;

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}


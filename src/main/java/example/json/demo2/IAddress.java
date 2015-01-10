package example.json.demo2;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by geek on 12/3/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public interface IAddress extends A, B {

    @XmlPath("/address/@streetAddress")
    String getStreet();

    void setStreet(String street);

    @XmlPath("/address/city/@cityName")
    String getCity();

    void setCity(String city);

    @XmlPath("/address/state/@stateCode")
    String getState();

    void setState(String state);
    @XmlPath("/address/country/@countryCode")
    String getCountry();

    void setCountry(String country);

    @XmlPath("/address/@postalCode")
    String getPostalCode();

    void setPostalCode(String postalCode);
}

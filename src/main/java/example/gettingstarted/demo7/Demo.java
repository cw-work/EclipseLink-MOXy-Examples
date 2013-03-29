package example.gettingstarted.demo7;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

import example.gettingstarted.demo1.Address;
import example.gettingstarted.demo1.Customer;
import example.gettingstarted.demo1.PhoneNumber;

/**
 * @author barry
 * make Customer.Address.street as Customer.@street
 */
public class Demo {

    @SuppressWarnings({ "rawtypes", "deprecation" })
    public static void main(String[] args) throws JAXBException {
        // Step 1 - Create the Domain Model
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        Address address = new Address();
        address.setStreet("123 Any Street");
        address.setCity("My Town");
        customer.setAddress(address);
=======
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
 
public class Demo {
 
    public static void main(String[] args) throws JAXBException {
 
        // Step 1 - Create the Domain Model
 
        Customer customer = new Customer();
        customer.setName("Jane Doe");
 
        Address address = new Address();
        address.setStreet("123 Any Street");
        address.setCity("My Town");
        address.setCustomer(customer);
        customer.setAddress(address);
 
>>>>>>> add samples about XmlID, XmlIDREF, XmlInverseReference
        PhoneNumber workPhoneNumber = new PhoneNumber();
        workPhoneNumber.setType("work");
        workPhoneNumber.setValue("613-555-1111");
        customer.getPhoneNumbers().add(workPhoneNumber);
<<<<<<< HEAD
=======
 
>>>>>>> add samples about XmlID, XmlIDREF, XmlInverseReference
        PhoneNumber cellPhoneNumber = new PhoneNumber();
        cellPhoneNumber.setType("cell");
        cellPhoneNumber.setValue("613-555-2222");
        customer.getPhoneNumbers().add(cellPhoneNumber);
<<<<<<< HEAD
        
        // Step 2 - Convert the Domain Model to XML
        final Map<String, Source> metadataSourceMap = new HashMap<String, Source>();
        metadataSourceMap.put("example.gettingstarted.demo1", new StreamSource("./example/gettingstarted/demo5/eclipselink-oxm.xml"));
        
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, metadataSourceMap);
        
        final Class[] classes = new Class[1];
        classes[0] = Customer.class;
        
        JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(classes, properties);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer, System.out);
    }
}
=======
 
        // Step 2 - Convert the Domain Model to XML
 
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
 
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
        JAXBElement<Customer> jaxbElement = new JAXBElement<Customer>(new QName(null, "customer"), Customer.class, customer);
        marshaller.marshal(jaxbElement, System.out);
 
    }
 
}
>>>>>>> add samples about XmlID, XmlIDREF, XmlInverseReference

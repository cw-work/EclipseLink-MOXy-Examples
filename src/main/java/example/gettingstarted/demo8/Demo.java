package example.gettingstarted.demo8;

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
        PhoneNumber workPhoneNumber = new PhoneNumber();
        workPhoneNumber.setType("work");
        workPhoneNumber.setValue("613-555-1111");
        customer.getPhoneNumbers().add(workPhoneNumber);
        PhoneNumber cellPhoneNumber = new PhoneNumber();
        cellPhoneNumber.setType("cell");
        cellPhoneNumber.setValue("613-555-2222");
        customer.getPhoneNumbers().add(cellPhoneNumber);
        // Step 2 - Convert the Domain Model to XML
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        JAXBElement<Customer> jaxbElement = new JAXBElement<Customer>(new QName(null, "customer"), Customer.class, customer);
        marshaller.marshal(jaxbElement, System.out);
    }
}

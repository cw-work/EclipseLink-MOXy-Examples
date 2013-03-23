package example.dynamic.demo1;

import java.io.FileInputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContext;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContextFactory;

public class Demo {

    public static void main(String[] args) throws Exception {
        final DynamicJAXBContext jaxbContext = DynamicJAXBContextFactory.createContextFromXSD(Demo.class.getResourceAsStream("xsd/customer.xsd"),
                new MyEntityResolver(), null, null);
        final FileInputStream xmlInputStream = new FileInputStream("src/main/java/example/dynamic/demo1/customer.xml");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final DynamicEntity customer = (DynamicEntity) unmarshaller.unmarshal(xmlInputStream);
        System.out.println(customer.<String> get("name"));
        final DynamicType addressType = jaxbContext.getDynamicType("org.example.address.Address");
        final DynamicEntity address = customer.<DynamicEntity> get("address");
        for ( final String propertyName : addressType.getPropertiesNames() ) {
            System.out.println(address.get(propertyName));
        }
        
        test();
    }

    public static void test() throws JAXBException {
        final DynamicJAXBContext jaxbContext = DynamicJAXBContextFactory.createContextFromXSD(Demo.class.getResourceAsStream("xsd/customer.xsd"),
                new MyEntityResolver(), null, null);
        DynamicEntity customer = jaxbContext.newDynamicEntity("org.example.customer.Customer");
        customer.set("name", "Jane Doe");
        DynamicEntity address = jaxbContext.newDynamicEntity("org.example.address.Address");
        address.set("street", "1 Any Street").set("city", "Any Town");
        customer.set("address", address);
        
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer, System.out);
    }
}

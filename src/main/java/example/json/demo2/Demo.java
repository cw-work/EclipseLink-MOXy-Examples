package example.json.demo2;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class Demo {


    public static void main(String[] args) throws Exception {
        final FileReader xmlURL = new FileReader("src/main/java/example/json/demo2/response.xml");
        final FileReader jsonURL = new FileReader("src/main/java/example/json/demo2/response.json");

        JAXBContext jc = JAXBContextFactory.createContext(new Class[]{Address.class, IAddress.class}, new HashMap());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // XML
        XMLInputFactory xif = XMLInputFactory.newInstance();
        StreamSource xml = new StreamSource(xmlURL);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag(); // Advance to kml tag
        xsr.nextTag(); // Advance to Response tag
        JAXBElement<Address> addressFromXML = unmarshaller.unmarshal(xsr, Address.class);
        marshaller.marshal(addressFromXML, System.out);

        // JSON
        unmarshaller.setProperty("eclipselink.media-type", "application/json");
        unmarshaller.setProperty("eclipselink.json.include-root", false);
        StreamSource json = new StreamSource(jsonURL);

        final JAXBElement<Address> addressFromJSON = unmarshaller.unmarshal(json, Address.class);
        marshaller.setProperty("eclipselink.media-type", "application/json");
        marshaller.setProperty("eclipselink.json.include-root", false);
        final IAddress value = (IAddress) Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class[]{IAddress.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                final Address address = addressFromJSON.getValue();
                return method.invoke(address, args);
            }
        });
        marshaller.marshal(value, System.out);
    }
}
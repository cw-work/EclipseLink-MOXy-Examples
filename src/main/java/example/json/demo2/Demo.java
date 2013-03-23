package example.json.demo2;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class Demo {


    public static void main(String[] args) throws Exception {
        final FileReader xmlURL = new FileReader("src/main/java/example/json/demo2/response.xml");
        final FileReader jsonURL = new FileReader("src/main/java/example/json/demo2/response.json");
        
        JAXBContext jc = JAXBContext.newInstance(Address.class);
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
        JAXBElement<Address> addressFromJSON = unmarshaller.unmarshal(json, Address.class);
        marshaller.setProperty("eclipselink.media-type", "application/json");
        marshaller.setProperty("eclipselink.json.include-root", false);
        marshaller.marshal(addressFromJSON, System.out);
    }
}
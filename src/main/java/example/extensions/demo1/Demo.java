package example.extensions.demo1;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Demo {
	public static void main(String[] args) throws JAXBException {
		File f = new File("src/main/java/example/extensions/demo1/input.xml");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

		Customer c = (Customer) jaxbContext.createUnmarshaller().unmarshal(f);

		System.out.println(c);
	}
}

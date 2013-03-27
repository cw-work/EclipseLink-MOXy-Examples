package example.extensions.demo1;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlLocation;

import org.xml.sax.Locator;

@XmlRootElement
public class Customer {

	public int id;

	public String name;

	@XmlLocation
	@XmlTransient
	public Locator locator;

	@Override
	public String toString() {
		String loc = " noLoc";
		if (locator != null) {
			loc = " L" + locator.getLineNumber() + " C"
					+ locator.getColumnNumber() + " " + locator.getSystemId();
		}

		return "Customer(" + name + ")" + loc;
	}

}

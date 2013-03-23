@XmlSchema(namespace = "http://earth.google.com/kml/2.0", 
           elementFormDefault = XmlNsForm.QUALIFIED, 
           xmlns = { 
               @XmlNs(prefix = "ns",
                   namespaceURI = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0"
               ) 
           }
)
@XmlAccessorType(XmlAccessType.FIELD)
package example.json.demo2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;


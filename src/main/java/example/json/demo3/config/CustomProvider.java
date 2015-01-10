package example.json.demo3.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

//import net.madz.db.core.meta.immutable.mysql.MySQLSchemaMetaData;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

@Provider
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CustomProvider extends MOXyJsonProvider {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return false;//getDomainClass(genericType) == MySQLSchemaMetaData.class;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return isReadable(type, genericType, annotations, mediaType);
    }

    @Override
    protected void preReadFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            Unmarshaller unmarshaller) throws JAXBException {
        unmarshaller.setProperty(MarshallerProperties.JSON_VALUE_WRAPPER, "$");
    }

    @Override
    protected JAXBContext getJAXBContext(Class<?> domainClass, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, ?> httpHeaders)
            throws JAXBException {
        ContextResolver<JAXBContext> resolver = providers.getContextResolver(JAXBContext.class, mediaType);
        JAXBContext jaxbContext;
        if ( null == resolver || null == ( jaxbContext = resolver.getContext(domainClass) ) ) {
            final List<Source> metadataSourceList = new LinkedList<Source>();
            metadataSourceList.add(new StreamSource("./example/schema/demo1/dotted-path-oxm.xml"));
            metadataSourceList.add(new StreamSource("./example/schema/demo1/schema-oxm.xml"));
            metadataSourceList.add(new StreamSource("./example/schema/demo1/mysql-schema-oxm.xml"));
            final Map<String, Object> properties = new HashMap<String, Object>();
            properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, metadataSourceList);
            final Class[] classes = new Class[1];
            //classes[0] = MySQLSchemaMetaData.class;
            jaxbContext = (JAXBContext) JAXBContext.newInstance(classes, properties);
            return jaxbContext;
        } else if ( jaxbContext instanceof org.eclipse.persistence.jaxb.JAXBContext ) {
            return jaxbContext;
        } else {
            return JAXBContextFactory.createContext(new Class[] { domainClass }, null);
        }
    }

    @Override
    protected void preWriteTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, Marshaller marshaller) throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if ( MediaType.APPLICATION_XML.equals(mediaType.toString()) ) {
            marshaller.setProperty("eclipselink.media-type", MediaType.APPLICATION_XML);
        } else {
            marshaller.setProperty(MarshallerProperties.JSON_VALUE_WRAPPER, "$");
        }
    }
}
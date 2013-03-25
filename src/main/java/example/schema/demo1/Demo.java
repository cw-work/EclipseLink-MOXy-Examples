package example.schema.demo1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.madz.db.core.AbsSchemaMetaDataParser;
import net.madz.db.core.impl.DbOperatorFactoryImpl;
import net.madz.db.core.meta.immutable.SchemaMetaData;
import net.madz.db.core.meta.immutable.mysql.MySQLSchemaMetaData;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

import example.json.demo2.Address;

public class Demo {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws SQLException, JAXBException {
        // Step 1 - Create the Domain Model
        final AbsSchemaMetaDataParser sourceDbParser = DbOperatorFactoryImpl.getInstance().createSchemaParser("fortest", false);
        final SchemaMetaData schemaMetaData = sourceDbParser.parseSchemaMetaData();
        // Step 2 - Convert the Domain Model to XML
        // final Map<String, Source> metadataSourceMap = new HashMap<String,
        // Source>();
        // metadataSourceMap.put("net.madz.db.core.meta.immutable.mysql", new
        // StreamSource("./example/schema/demo1/eclipselink-oxm.xml"));
        // metadataSourceMap.put("net.madz.db.core.meta", new
        // StreamSource("./example/schema/demo1/dotted-path-oxm.xml"));
        // metadataSourceMap.put("net.madz.db.core.meta.immutable", new
        // StreamSource("./example/schema/demo1/schema-oxm.xml"));
        final List<Source> metadataSourceList = new LinkedList<Source>();
        metadataSourceList.add(new StreamSource("./example/schema/demo1/dotted-path-oxm.xml"));
        metadataSourceList.add(new StreamSource("./example/schema/demo1/schema-oxm.xml"));
        metadataSourceList.add(new StreamSource("./example/schema/demo1/mysql-schema-oxm.xml"));
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, metadataSourceList);
        final Class[] classes = new Class[1];
        classes[0] = MySQLSchemaMetaData.class;
        JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(classes, properties);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal((MySQLSchemaMetaData) schemaMetaData, System.out);
//
        // JSON
        marshaller.setProperty("eclipselink.media-type", "application/json");
        marshaller.setProperty("eclipselink.json.include-root", false);
        marshaller.marshal((MySQLSchemaMetaData) schemaMetaData, System.out);
    }
}

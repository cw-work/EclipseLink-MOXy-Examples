package example.json.demo3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import example.json.demo3.config.CustomProvider;
import example.json.demo3.resources.AircraftTypeList;
import example.json.demo3.resources.FlightList;

public class CustomApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>(2);
        set.add(MOXyJsonProvider.class);
        set.add(CustomProvider.class);
        set.add(AircraftTypeList.class);
        set.add(FlightList.class);
        return set;
    }
    
    @Override
    public Set<Object> getSingletons() {
        MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
 
        moxyJsonProvider.setAttributePrefix("@");
        moxyJsonProvider.setFormattedOutput(true);
        moxyJsonProvider.setIncludeRoot(true);
        moxyJsonProvider.setMarshalEmptyCollections(false);
        moxyJsonProvider.setValueWrapper("$");
        moxyJsonProvider.setIncludeRoot(false);
 
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.example.org/customer", "cust");
        moxyJsonProvider.setNamespacePrefixMapper(namespacePrefixMapper);
        moxyJsonProvider.setNamespaceSeparator(':');
 
        HashSet<Object> set = new HashSet<Object>(1);
        set.add(moxyJsonProvider);
        return set;
    }
}

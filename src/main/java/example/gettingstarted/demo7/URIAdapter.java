package example.gettingstarted.demo7;

import java.net.URI;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class URIAdapter extends XmlAdapter<URI, String> {

    @Override
    public String unmarshal(URI v) throws Exception {
        return v.getFragment();
    }

    @Override
    public URI marshal(String v) throws Exception {
        return new URI("http", "127.0.0.1", "/customer/" + v, "");
    }
}

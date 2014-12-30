package org.jibx.schema.org.opentravel._2014B.ping.ws.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * The ping web service factory class.
 */
@WebServiceClient(name = "PingService", 
                  wsdlLocation = "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/ping.wsdl",
                  targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws") 
public class PingSoapService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.opentravel.org/OTA/2003/05/ws", "PingService");
    public final static QName Soap = new QName("http://www.opentravel.org/OTA/2003/05/ws", "soap");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/ping.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PingSoapService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/ping.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PingSoapService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PingSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PingSoapService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns Ping
     */
    @WebEndpoint(name = "soap")
    public Ping getSoap() {
        return super.getPort(Soap, Ping.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Ping
     */
    @WebEndpoint(name = "soap")
    public Ping getSoap(WebServiceFeature... features) {
        return super.getPort(Soap, Ping.class, features);
    }

}

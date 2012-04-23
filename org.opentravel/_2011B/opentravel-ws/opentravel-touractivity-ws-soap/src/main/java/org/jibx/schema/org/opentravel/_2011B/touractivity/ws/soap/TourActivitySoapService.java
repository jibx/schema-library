package org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * The touractivity web service factory class.
 */
@WebServiceClient(name = "TourActivityService", 
                  wsdlLocation = "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/touractivity.wsdl",
                  targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws") 
public class TourActivitySoapService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.opentravel.org/OTA/2003/05/ws", "TourActivityService");
    public final static QName Soap = new QName("http://www.opentravel.org/OTA/2003/05/ws", "soap");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/touractivity.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(TourActivitySoapService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/touractivity.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TourActivitySoapService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TourActivitySoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TourActivitySoapService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns TourActivity
     */
    @WebEndpoint(name = "soap")
    public TourActivity getSoap() {
        return super.getPort(Soap, TourActivity.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TourActivity
     */
    @WebEndpoint(name = "soap")
    public TourActivity getSoap(WebServiceFeature... features) {
        return super.getPort(Soap, TourActivity.class, features);
    }

}

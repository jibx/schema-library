package org.jibx.schema.org.opentravel.x2013B.hotel.ws.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * The hotel web service factory class.
 */
@WebServiceClient(name = "HotelService", 
                  wsdlLocation = "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/hotel.wsdl",
                  targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws") 
public class HotelSoapService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.opentravel.org/OTA/2003/05/ws", "HotelService");
    public final static QName Soap = new QName("http://www.opentravel.org/OTA/2003/05/ws", "soap");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/hotel.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(HotelSoapService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/don/workspace-fuse/samples/opentravel-osgi-sample/opentravel-cxfse-bundle/src/main/resources/hotel.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public HotelSoapService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HotelSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HotelSoapService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns Hotel
     */
    @WebEndpoint(name = "soap")
    public Hotel getSoap() {
        return super.getPort(Soap, Hotel.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Hotel
     */
    @WebEndpoint(name = "soap")
    public Hotel getSoap(WebServiceFeature... features) {
        return super.getPort(Soap, Hotel.class, features);
    }

}

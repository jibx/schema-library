package org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.cxf.annotations.DataBinding;
import org.jibx.schema.org.opentravel._2011B.touractivity.*;

/**
 * The web services interface.
 */
@WebService(targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", name = "TourActivity")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@DataBinding(org.apache.cxf.jibx.JibxDataBinding.class)
public interface TourActivity {

    @WebResult(name = "TourActivityAvailRS", targetNamespace = "http://www.opentravel.org/OTA/2003/05", partName = "payload")
    @WebMethod(operationName = "TourActivityAvail")
    public AvailRS avail(
        @WebParam(partName = "payload", name = "TourActivityAvailRQ", targetNamespace = "http://www.opentravel.org/OTA/2003/05")
        	AvailRQ payload
    );
}

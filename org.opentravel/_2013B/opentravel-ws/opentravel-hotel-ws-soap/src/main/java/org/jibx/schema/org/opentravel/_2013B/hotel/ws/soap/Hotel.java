package org.jibx.schema.org.opentravel._2013B.hotel.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.cxf.annotations.DataBinding;
import org.jibx.schema.org.opentravel._2013B.hotel.*;

/**
 * The web services interface.
 */
@WebService(targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", name = "Hotel")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@DataBinding(org.apache.cxf.jibx.JibxDataBinding.class)
public interface Hotel {

    @WebResult(name = "HotelResRS", targetNamespace = "http://www.opentravel.org/OTA/2003/05", partName = "payload")
    @WebMethod(operationName = "HotelRes")
    public ResRS res(
        @WebParam(partName = "payload", name = "HotelResRQ", targetNamespace = "http://www.opentravel.org/OTA/2003/05")
        	ResRQ payload
    );

    @WebResult(name = "HotelResModifyRS", targetNamespace = "http://www.opentravel.org/OTA/2003/05", partName = "payload")
    @WebMethod(operationName = "HotelResModify")
    public ResModifyRS resModify(
        @WebParam(partName = "payload", name = "HotelResModifyRQ", targetNamespace = "http://www.opentravel.org/OTA/2003/05")
        	ResModifyRQ payload
    );
}

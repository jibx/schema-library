package org.jibx.schema.org.opentravel._2011A.ping.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.cxf.annotations.DataBinding;
import org.jibx.schema.org.opentravel._2011A.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011A.ping.PingRS;

/**
 * The web services interface.
 */
@WebService(targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", name = "Ping")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@DataBinding(org.apache.cxf.jibx.JibxDataBinding.class)
public interface Ping {

    @WebResult(name = "PingRS", targetNamespace = "http://www.opentravel.org/OTA/2003/05", partName = "payload")
    @WebMethod(operationName = "SendPing")
    public PingRS sendPing(
        @WebParam(partName = "payload", name = "PingRQ", targetNamespace = "http://www.opentravel.org/OTA/2003/05")
        	PingRQ payload
    );
}

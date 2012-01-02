package org.jibx.schema.org.opentravel._2011B.ping.ws.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.FreeText;
import org.jibx.schema.org.opentravel._2011B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2011B.base.Success;
import org.jibx.schema.org.opentravel._2011B.base._Error;
import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS.Sequence;
import org.jibx.schema.org.opentravel._2011B.ping.ws.Ping;
import org.joda.time.DateTime;

@WebService(serviceName = "PingService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel._2011B.ping.ws.Ping")
public class PingImpl implements Ping {

	public PingRS sendPing(PingRQ ping) {

		PingRS response = new PingRS();
		response.setPayloadStdAttributes(createStandardPayload());
		
        if (ping.getEchoData() == null || ping.getEchoData().length() == 0) {
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error, empty echo data");
        	errors.addError(error);
        	response.setErrors(errors);
        	return response;
        }

        Sequence sequence = new Sequence();
        sequence.setSuccess(new Success());
        sequence.setEchoData(ping.getEchoData());
        response.addSuccess(sequence);
		
        return response;
	}
    /**
     * Shared method to set up a default payload.
     * @return The payload
     */
    public OTAPayloadStdAttributes createStandardPayload()
    {
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        DateTime timeStamp = new DateTime();
        payloadStdAttributes.setTimeStamp(timeStamp);
        Float version = new Float(1.00);
        payloadStdAttributes.setVersion(version);
        payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.PRODUCTION);
        return payloadStdAttributes;
    }
}

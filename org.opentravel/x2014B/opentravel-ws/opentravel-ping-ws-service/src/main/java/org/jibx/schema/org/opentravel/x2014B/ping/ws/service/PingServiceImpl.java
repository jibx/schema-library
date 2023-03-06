package org.jibx.schema.org.opentravel.x2014B.ping.ws.service;

import org.jibx.schema.org.opentravel.x2014B.base.Errors;
import org.jibx.schema.org.opentravel.x2014B.base.Success;
import org.jibx.schema.org.opentravel.x2014B.ping.PingRQ;
import org.jibx.schema.org.opentravel.x2014B.ping.PingRS;
import org.jibx.schema.org.opentravel.x2014B.ping.PingRS.Sequence;
import org.jibx.schema.org.opentravel.x2014B.ping.ws.impl.DefaultPingService;

public class PingServiceImpl extends DefaultPingService
{

	/**
	 * Constructor
	 */
	public PingServiceImpl()
	{
		super();
	}

	/**
	 * Constructor
	 */
	public void init()
	{
		super.init();
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request) {

		PingRS response = new PingRS();
		response.setPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
        if (request.getEchoData() == null || request.getEchoData().length() == 0) {
        	Errors errors = addError(null, "Error, empty echo data", null);
        	response.setErrors(errors);
        	return response;
        }

        Sequence sequence = new Sequence();
        sequence.setSuccess(new Success());
        sequence.setEchoData(request.getEchoData());
        response.addSuccess(sequence);
		
        return response;
	}

}

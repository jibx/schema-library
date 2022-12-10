package org.jibx.schema.org.opentravel._2012B.ping.ws.impl;

import org.jibx.schema.org.opentravel._2012B.base.Errors;
import org.jibx.schema.org.opentravel._2012B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2012B.base.Success;
import org.jibx.schema.org.opentravel._2012B.base._Error;
import org.jibx.schema.org.opentravel._2012B.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2012B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2012B.ping.PingRS;
import org.jibx.schema.org.opentravel._2012B.ping.PingRS.Sequence;
import org.jibx.schema.org.opentravel._2012B.ping.ws.PingService;
import org.joda.time.DateTime;

/**
 * Skeleton ping service implementation.
 * @author don
 *
 */
public abstract class DefaultPingService extends BaseService
	implements PingService
{

	/**
	 * Constructor
	 */
	public DefaultPingService()
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
	 * You should override this method.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request) {
		
		PingRS response = new PingRS();
		response.setPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
    	Errors errors = addError(null, "Error, ping method not implemented", null);
    	response.setErrors(errors);
    	return response;
	}

}

package org.jibx.schema.org.opentravel._2013A.rail.ws.impl;

import org.jibx.schema.org.opentravel._2013A.rail.ws.*;
import org.jibx.schema.org.opentravel._2013A.rail.*;
import org.jibx.schema.org.opentravel._2013A.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton rail service implementation.
 * @author don
 *
 */
public abstract class DefaultRailService extends BaseService
	implements RailService
{

	/**
	 * Constructor
	 */
	public DefaultRailService()
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
	 * Service the xyz request.
	 * You should override this method.
	 * @param request
	 * @return
	 */
	/* TODO public XyzRS xyz(XyzRQ request) {
		
		PingRS response = new PingRS();
		response.setPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
		Errors errors = addError(null, "Error, ping method not implemented", null);
		response.setErrors(errors);
		return response;
	}*/

}

package org.jibx.schema.org.opentravel.x2013A.golf.ws.impl;

import org.jibx.schema.org.opentravel.x2013A.golf.ws.*;
import org.jibx.schema.org.opentravel.x2013A.golf.*;
import org.jibx.schema.org.opentravel.x2013A.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton hotel service implementation.
 * @author don
 *
 */
public abstract class DefaultGolfService extends BaseService
	implements GolfService
{

	/**
	 * Constructor
	 */
	public DefaultGolfService()
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

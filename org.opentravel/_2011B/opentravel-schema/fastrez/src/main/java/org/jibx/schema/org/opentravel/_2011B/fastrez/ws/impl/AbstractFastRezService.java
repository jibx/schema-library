package org.jibx.schema.org.opentravel._2011B.fastrez.ws.impl;

import org.jibx.schema.org.opentravel._2011B.fastrez.ws.*;
import org.jibx.schema.org.opentravel._2011B.fastrez.*;
import org.joda.time.DateTime;

/**
 * Skeleton FastRez service implementation.
 * @author don
 *
 */
public abstract class AbstractFastRezService extends Object //BaseService
	implements FastRezService
{

	/**
	 * Constructor
	 */
	public AbstractFastRezService()
	{
		super();
	}

	/**
	 * Constructor
	 */
	public void init()
	{
		//super.init();
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

package org.jibx.schema.org.opentravel._2014B.cruise.ws.impl;

import org.jibx.schema.org.opentravel._2014B.cruise.ws.*;
import org.jibx.schema.org.opentravel._2014B.cruise.*;
import org.jibx.schema.org.opentravel._2014B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton cruise service implementation.
 * @author don
 *
 */
public abstract class DefaultCruiseService extends BaseService
	implements CruiseService
{

	/**
	 * Constructor
	 */
	public DefaultCruiseService()
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

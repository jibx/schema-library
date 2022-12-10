package org.jibx.schema.org.opentravel.x2014A.destactivity.ws.impl;

import org.jibx.schema.org.opentravel.x2014A.destactivity.*;
import org.jibx.schema.org.opentravel.x2014A.destactivity.ws.*;
import org.jibx.schema.org.opentravel.x2014A.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton destactivity service implementation.
 * @author don
 *
 */
public abstract class DefaultDestActivityService extends BaseService
	implements DestActivityService
{

	/**
	 * Constructor
	 */
	public DefaultDestActivityService()
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

package org.jibx.schema.org.opentravel._2013B.veh.ws.impl;

import org.jibx.schema.org.opentravel._2013B.veh.ws.*;
import org.jibx.schema.org.opentravel._2013B.veh.*;
import org.jibx.schema.org.opentravel._2013B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton veh service implementation.
 * @author don
 *
 */
public abstract class DefaultVehService extends BaseService
	implements VehService
{

	/**
	 * Constructor
	 */
	public DefaultVehService()
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

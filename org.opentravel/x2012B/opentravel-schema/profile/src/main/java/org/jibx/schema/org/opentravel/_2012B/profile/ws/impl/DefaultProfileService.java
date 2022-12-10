package org.jibx.schema.org.opentravel.x2012B.profile.ws.impl;

import org.jibx.schema.org.opentravel.x2012B.profile.ws.*;
import org.jibx.schema.org.opentravel.x2012B.profile.*;
import org.jibx.schema.org.opentravel.x2012B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton profile service implementation.
 * @author don
 *
 */
public abstract class DefaultProfileService extends BaseService
	implements ProfileService
{

	/**
	 * Constructor
	 */
	public DefaultProfileService()
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

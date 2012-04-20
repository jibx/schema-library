package org.jibx.schema.org.opentravel._2011B.ground.ws.impl;

import org.jibx.schema.org.opentravel._2011B.ground.ws.*;
import org.jibx.schema.org.opentravel._2011B.ground.*;
import org.jibx.schema.org.opentravel._2011B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton ground service implementation.
 * @author don
 *
 */
public abstract class AbstractGroundService extends BaseService
	implements GroundService
{

	/**
	 * Constructor
	 */
	public AbstractGroundService()
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

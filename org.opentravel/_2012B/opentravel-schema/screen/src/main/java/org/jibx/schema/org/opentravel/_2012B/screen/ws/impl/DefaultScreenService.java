package org.jibx.schema.org.opentravel._2012B.screen.ws.impl;

import org.jibx.schema.org.opentravel._2012B.screen.ws.*;
import org.jibx.schema.org.opentravel._2012B.screen.*;
import org.jibx.schema.org.opentravel._2012B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton screen service implementation.
 * @author don
 *
 */
public abstract class DefaultScreenService extends BaseService
	implements ScreenService
{

	/**
	 * Constructor
	 */
	public DefaultScreenService()
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

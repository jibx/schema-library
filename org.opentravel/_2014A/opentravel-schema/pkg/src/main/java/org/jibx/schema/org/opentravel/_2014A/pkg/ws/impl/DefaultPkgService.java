package org.jibx.schema.org.opentravel._2014A.pkg.ws.impl;

import org.jibx.schema.org.opentravel._2014A.pkg.ws.*;
import org.jibx.schema.org.opentravel._2014A.pkg.*;
import org.jibx.schema.org.opentravel._2014A.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton hotel service implementation.
 * @author don
 *
 */
public abstract class DefaultPkgService extends BaseService
	implements PkgService
{

	/**
	 * Constructor
	 */
	public DefaultPkgService()
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

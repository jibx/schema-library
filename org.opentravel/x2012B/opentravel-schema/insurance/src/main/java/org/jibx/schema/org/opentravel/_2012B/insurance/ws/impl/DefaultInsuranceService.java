package org.jibx.schema.org.opentravel.x2012B.insurance.ws.impl;

import org.jibx.schema.org.opentravel.x2012B.insurance.ws.*;
import org.jibx.schema.org.opentravel.x2012B.insurance.*;
import org.jibx.schema.org.opentravel.x2012B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton hotel service implementation.
 * @author don
 *
 */
public abstract class DefaultInsuranceService extends BaseService
	implements InsuranceService
{

	/**
	 * Constructor
	 */
	public DefaultInsuranceService()
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

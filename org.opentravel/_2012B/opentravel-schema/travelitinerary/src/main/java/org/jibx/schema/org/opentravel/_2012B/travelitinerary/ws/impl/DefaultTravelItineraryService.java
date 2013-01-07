package org.jibx.schema.org.opentravel._2012B.travelitinerary.ws.impl;

import org.jibx.schema.org.opentravel._2012B.travelitinerary.ws.*;
import org.jibx.schema.org.opentravel._2012B.travelitinerary.*;
import org.jibx.schema.org.opentravel._2012B.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton travelitinerary service implementation.
 * @author don
 *
 */
public abstract class DefaultTravelItineraryService extends BaseService
	implements TravelItineraryService
{

	/**
	 * Constructor
	 */
	public DefaultTravelItineraryService()
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

package org.jibx.schema.org.opentravel._2013A.reviews.ws.impl;

import org.jibx.schema.org.opentravel._2013A.base.Errors;
import org.jibx.schema.org.opentravel._2013A.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2013A.base.Success;
import org.jibx.schema.org.opentravel._2013A.base._Error;
import org.jibx.schema.org.opentravel._2013A.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2013A.reviews.ReviewsRQ;
import org.jibx.schema.org.opentravel._2013A.reviews.ReviewsRS;
import org.jibx.schema.org.opentravel._2013A.reviews.ws.ReviewsService;
import org.joda.time.DateTime;

/**
 * Skeleton reviews service implementation.
 * @author don
 *
 */
public abstract class DefaultReviewsService extends BaseService
	implements ReviewsService
{

	/**
	 * Constructor
	 */
	public DefaultReviewsService()
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
	 * Service the reviews request.
	 * You should override this method.
	 * @param request
	 * @return
	 */
	public ReviewsRS reviews(ReviewsRQ request) {
		
		ReviewsRS response = new ReviewsRS();
		response.setPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
    	Errors errors = addError(null, "Error, reviews method not implemented", null);
    	response.setErrors(errors);
    	return response;
	}

}

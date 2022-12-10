package org.jibx.schema.org.opentravel._2014A.reviews.ws;

import org.jibx.schema.org.opentravel._2014A.reviews.ReviewsRQ;
import org.jibx.schema.org.opentravel._2014A.reviews.ReviewsRS;

public interface ReviewsService {

	/**
	 * Service the reviews request.
	 * @param request
	 * @return
	 */
	public ReviewsRS reviews(ReviewsRQ request);
}

package org.jibx.schema.org.opentravel._2014B.reviews.ws;

import org.jibx.schema.org.opentravel._2014B.reviews.ReviewsRQ;
import org.jibx.schema.org.opentravel._2014B.reviews.ReviewsRS;

public interface ReviewsService {

	/**
	 * Service the reviews request.
	 * @param request
	 * @return
	 */
	public ReviewsRS reviews(ReviewsRQ request);
}

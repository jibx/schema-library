package org.jibx.schema.org.opentravel.x2013B.reviews.ws;

import org.jibx.schema.org.opentravel.x2013B.reviews.ReviewsRQ;
import org.jibx.schema.org.opentravel.x2013B.reviews.ReviewsRS;

public interface ReviewsService {

	/**
	 * Service the reviews request.
	 * @param request
	 * @return
	 */
	public ReviewsRS reviews(ReviewsRQ request);
}

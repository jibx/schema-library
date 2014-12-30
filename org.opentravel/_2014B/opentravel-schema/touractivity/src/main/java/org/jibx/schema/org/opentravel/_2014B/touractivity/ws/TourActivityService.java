package org.jibx.schema.org.opentravel._2014B.touractivity.ws;

import org.jibx.schema.org.opentravel._2014B.touractivity.*;
import org.jibx.schema.org.opentravel._2014B.misc.ReadRQ;

public interface TourActivityService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailRS avail(AvailRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookRS book(BookRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CancelRS cancel(CancelRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookRS modify(ModifyRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResRetrieveRS resRetrieve(ReadRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public SearchRS search(SearchRQ request);
}

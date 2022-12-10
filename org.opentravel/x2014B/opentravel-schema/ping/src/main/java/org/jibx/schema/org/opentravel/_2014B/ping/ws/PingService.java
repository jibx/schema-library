package org.jibx.schema.org.opentravel.x2014B.ping.ws;

import org.jibx.schema.org.opentravel.x2014B.ping.PingRQ;
import org.jibx.schema.org.opentravel.x2014B.ping.PingRS;

public interface PingService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request);
}

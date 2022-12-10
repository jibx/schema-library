package org.jibx.schema.org.opentravel.x2012A.ping.ws;

import org.jibx.schema.org.opentravel.x2012A.ping.PingRQ;
import org.jibx.schema.org.opentravel.x2012A.ping.PingRS;

public interface PingService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request);
}

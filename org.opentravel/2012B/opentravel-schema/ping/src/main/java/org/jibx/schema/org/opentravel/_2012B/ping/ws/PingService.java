package org.jibx.schema.org.opentravel._2012B.ping.ws;

import org.jibx.schema.org.opentravel._2012B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2012B.ping.PingRS;

public interface PingService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request);
}

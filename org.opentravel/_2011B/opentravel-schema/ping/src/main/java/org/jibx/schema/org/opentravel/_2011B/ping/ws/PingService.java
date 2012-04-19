package org.jibx.schema.org.opentravel._2011B.ping.ws;

import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;

public interface PingService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public abstract PingRS ping(PingRQ request);
}

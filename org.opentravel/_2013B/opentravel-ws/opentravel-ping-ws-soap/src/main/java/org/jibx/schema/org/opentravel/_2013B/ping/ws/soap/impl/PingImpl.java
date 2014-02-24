package org.jibx.schema.org.opentravel._2013B.ping.ws.soap.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel._2013B.base.Errors;
import org.jibx.schema.org.opentravel._2013B.base.FreeText;
import org.jibx.schema.org.opentravel._2013B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2013B.base.Success;
import org.jibx.schema.org.opentravel._2013B.base._Error;
import org.jibx.schema.org.opentravel._2013B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2013B.ping.PingRS;
import org.jibx.schema.org.opentravel._2013B.ping.PingRS.Sequence;
import org.jibx.schema.org.opentravel._2013B.ping.ws.soap.Ping;
import org.jibx.schema.org.opentravel._2013B.ping.ws.PingService;
import org.joda.time.DateTime;

@WebService(serviceName = "PingService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel._2013B.ping.ws.soap.Ping")
public class PingImpl implements Ping {

	public PingRS ping(PingRQ request) {
		
		return getPingService().ping(request);
	}

	/**
	 * Get the ping service.
	 * @return
	 */
	public PingService getPingService()
	{
		return pingService;
	}
	/**
	 * Set the ping service.
	 * @return
	 */
	public void setPingService(PingService pingService)
	{
		this.pingService = pingService;
	}
	protected PingService pingService = null;
}

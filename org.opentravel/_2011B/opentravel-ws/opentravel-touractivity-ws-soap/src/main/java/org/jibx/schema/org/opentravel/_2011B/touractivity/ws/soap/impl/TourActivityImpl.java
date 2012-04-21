package org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.FreeText;
import org.jibx.schema.org.opentravel._2011B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2011B.base.Success;
import org.jibx.schema.org.opentravel._2011B.base._Error;
import org.jibx.schema.org.opentravel._2011B.touractivity.*;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.service.TourActivityServiceImpl;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.TourActivity;
import org.joda.time.DateTime;

@WebService(serviceName = "TourActivityService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.TourActivity")
public class TourActivityImpl implements TourActivity {

	public AvailRS avail(AvailRQ request) {
		
		return getTourActivityService().avail(request);
	}

	/**
	 * Get the touractivity service.
	 * @return
	 */
	public TourActivityService getTourActivityService()
	{
		if (touractivityService == null)
			touractivityService = new TourActivityServiceImpl();
		return touractivityService;
	}
	protected TourActivityService touractivityService = null;
}

package org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRQ;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRS;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.TourActivity;

@WebService(serviceName = "TourActivityService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel._2011B.touractivity.ws.soap.TourActivity")
public class TourActivityImpl
	implements TourActivity {

	public AvailRS avail(AvailRQ request) {
		
        AvailRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().avail(request);

        return response;
	}

	/**
	 * Get the touractivity service.
	 * @return
	 */
	public TourActivityService getTourActivityService()
	{
		return tourActivityService;
	}
	/**
	 * Set the touractivity service.
	 * @param tourActivityService
	 */
	public void setTourActivityService(TourActivityService tourActivityService)
	{
		this.tourActivityService = tourActivityService;
	}
	protected TourActivityService tourActivityService = null;

}

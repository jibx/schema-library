package org.jibx.schema.org.opentravel.x2014A.touractivity.ws.soap.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel.x2014A.touractivity.AvailRQ;
import org.jibx.schema.org.opentravel.x2014A.touractivity.AvailRS;
import org.jibx.schema.org.opentravel.x2014A.touractivity.SearchRQ;
import org.jibx.schema.org.opentravel.x2014A.touractivity.SearchRS;
import org.jibx.schema.org.opentravel.x2014A.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel.x2014A.touractivity.ws.soap.TourActivity;

@WebService(serviceName = "TourActivityService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel.x2014A.touractivity.ws.soap.TourActivity")
public class TourActivityImpl
	implements TourActivity {

	public AvailRS avail(AvailRQ request) {
		
        AvailRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().avail(request);

        return response;
	}

	public SearchRS search(SearchRQ request) {
		
        SearchRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().search(request);

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

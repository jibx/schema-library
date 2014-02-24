package org.jibx.schema.org.opentravel._2013B.hotel.ws.soap.impl;

import javax.jws.WebService;

import org.jibx.schema.org.opentravel._2013B.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2013B.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2013B.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2013B.hotel.ResRS;
import org.jibx.schema.org.opentravel._2013B.hotel.ws.HotelService;
import org.jibx.schema.org.opentravel._2013B.hotel.ws.soap.Hotel;

@WebService(serviceName = "HotelService", targetNamespace = "http://www.opentravel.org/OTA/2003/05/ws", endpointInterface = "org.jibx.schema.org.opentravel._2013B.hotel.ws.soap.Hotel")
public class HotelImpl
	implements Hotel {

	public ResRS res(ResRQ request) {
		
        ResRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().res(request);

        return response;
	}

	public ResModifyRS resModify(ResModifyRQ request) {
		
        ResModifyRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().resModify(request);

        return response;
	}

	/**
	 * Get the hotel service.
	 * @return
	 */
	public HotelService getHotelService()
	{
		return hotelService;
	}
	/**
	 * Set the hotel service.
	 * @param hotelService
	 */
	public void setHotelService(HotelService hotelService)
	{
		this.hotelService = hotelService;
	}
	protected HotelService hotelService = null;

}

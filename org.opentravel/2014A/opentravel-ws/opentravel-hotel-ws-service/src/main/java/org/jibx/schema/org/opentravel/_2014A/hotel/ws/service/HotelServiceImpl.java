package org.jibx.schema.org.opentravel._2014A.hotel.ws.service;

import org.jibx.schema.org.opentravel._2014A.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2014A.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2014A.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2014A.hotel.ResRS;
import org.jibx.schema.org.opentravel._2014A.hotel.ws.impl.DefaultHotelService;

public class HotelServiceImpl extends DefaultHotelService
{

	/**
	 * Constructor
	 */
	public HotelServiceImpl()
	{
		super();
	}

	/**
	 * Constructor
	 */
	public void init()
	{
		super.init();
	}

	/**
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public ResRS res(ResRQ request) {

		ResRS response = createResRS(request);
				
        return response;
	}

	/**
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public ResModifyRS resModify(ResModifyRQ request) {

		ResModifyRS response = createResModifyRS(request);
				
        return response;
	}
}

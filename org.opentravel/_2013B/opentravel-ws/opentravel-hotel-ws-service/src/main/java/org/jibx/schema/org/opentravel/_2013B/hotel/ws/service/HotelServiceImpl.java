package org.jibx.schema.org.opentravel._2013B.hotel.ws.service;

import org.jibx.schema.org.opentravel._2013B.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2013B.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2013B.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2013B.hotel.ResRS;
import org.jibx.schema.org.opentravel._2013B.hotel.ws.impl.DefaultHotelService;

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

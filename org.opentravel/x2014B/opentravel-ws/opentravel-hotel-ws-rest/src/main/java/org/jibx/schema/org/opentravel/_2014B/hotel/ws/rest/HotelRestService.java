/**
 * Hotel RESTful service
 */
package org.jibx.schema.org.opentravel.x2014B.hotel.ws.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jibx.schema.org.opentravel.x2014B.base.ws.BaseService;
import org.jibx.schema.org.opentravel.x2014B.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel.x2014B.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel.x2014B.hotel.ResRQ;
import org.jibx.schema.org.opentravel.x2014B.hotel.ResRS;
import org.jibx.schema.org.opentravel.x2014B.hotel.SearchRQ;
import org.jibx.schema.org.opentravel.x2014B.hotel.SearchRS;
import org.jibx.schema.org.opentravel.x2014B.hotel.ws.HotelService;

@Path("/")
public class HotelRestService
{

	private Logger m_logger;
	
    public HotelRestService() {
    	super();
        init();
    }

    public void init() {
        m_logger = Logger.getLogger("org.jibx.schema.org.opentravel");
    	m_logger.info("Starting HotelRestService");
    }

    /**
     * Get a stored hotel message by tour code
     * @param id
     * @return
     */
    @GET
    @Path("/res/{id}/{date}/")
    @Produces("application/xml")
    public ResRS getRes(@PathParam("id") String id, @PathParam("date") String date) {
    	m_logger.info("Invoking get hotel/res (get), tour id is: " + id + ", date is: " + date);
        // Create a request
        ResRQ request = new ResRQ();
		request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
		//HotelID hotelID = new HotelID();
		//request.addHotelBasicInfo(hotelID);
		//hotelID.setHotelID(id);
		//Schedule schedule = new Schedule();
		//request.addSchedule(schedule);
		//schedule.addStartTime(date);

		ResRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().res(request);
		return response;
    }
    
    /**
     * Add (post) and hotel.
     * @param request
     * @return
     */
    @POST
    @Path("/res/")
    public ResRS postRes(ResRQ request) {
    	m_logger.info("Invoking add hotel/res (post)");
        ResRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().res(request);
        return response; //ResRS.ok().type("application/xml").entity(hotel).build();
    }

    /**
     * Add (post) and hotel.
     * @param request
     * @return
     */
    @POST
    @Path("/resmodify/")
    public ResModifyRS postResModify(ResModifyRQ request) {
    	m_logger.info("Invoking add hotel/res (post)");
        ResModifyRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().resModify(request);
        return response; //ResRS.ok().type("application/xml").entity(hotel).build();
    }

    /**
     * Search for tours.
     * @param id
     * @return
     */
    @GET
    @Path("/search/{startdate}/{enddate}/")
    @Produces("application/xml")
    public SearchRS getSearch(@PathParam("startdate") String startdate, @PathParam("enddate") String enddate) {
    	m_logger.info("Invoking get hotel/search (get), date range: " + startdate + ", to " + enddate);
        // Create a request
        SearchRQ request = new SearchRQ();
		request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
		//DateTimePref dateTimePref = new DateTimePref();
		//dateTimePref.setStart(startdate);
		//dateTimePref.setEnd(enddate);
		//request.setDateTimePref(dateTimePref);

		SearchRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().search(request);
		return response;
    }
    
    /**
     * Search (post) and hotel.
     * @param request
     * @return
     */
    @POST
    @Path("/search/")
    public SearchRS postSearch(SearchRQ request) {
    	m_logger.info("Invoking add hotel/search (post)");
        SearchRS response = null;
        if (getHotelService() != null)
        	response = getHotelService().search(request);
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

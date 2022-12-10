/**
 * TourActivity RESTful service
 */
package org.jibx.schema.org.opentravel._2014A.touractivity.ws.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jibx.schema.org.opentravel._2014A.base.touractivity.TourActivityID;
import org.jibx.schema.org.opentravel._2014A.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2014A.touractivity.AvailRQ;
import org.jibx.schema.org.opentravel._2014A.touractivity.AvailRQ.Schedule;
import org.jibx.schema.org.opentravel._2014A.touractivity.AvailRS;
import org.jibx.schema.org.opentravel._2014A.touractivity.SearchRQ;
import org.jibx.schema.org.opentravel._2014A.touractivity.SearchRQ.DateTimePref;
import org.jibx.schema.org.opentravel._2014A.touractivity.SearchRS;
import org.jibx.schema.org.opentravel._2014A.touractivity.ws.TourActivityService;

@Path("/")
public class TourActivityRestService
{

	private Logger m_logger;
	
    public TourActivityRestService() {
    	super();
        init();
    }

    public void init() {
        m_logger = Logger.getLogger("org.jibx.schema.org.opentravel");
    	m_logger.info("Starting TourActivityRestService");
    }

    /**
     * Get a stored touractivity message by tour code
     * @param id
     * @return
     */
    @GET
    @Path("/avail/{id}/{date}/")
    @Produces("application/xml")
    public AvailRS getAvail(@PathParam("id") String id, @PathParam("date") String date) {
    	m_logger.info("Invoking get touractivity/avail (get), tour id is: " + id + ", date is: " + date);
        // Create a request
        AvailRQ request = new AvailRQ();
		request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
		TourActivityID tourActivityID = new TourActivityID();
		request.addTourActivityBasicInfo(tourActivityID);
		tourActivityID.setTourActivityID(id);
		Schedule schedule = new Schedule();
		request.addSchedule(schedule);
		schedule.addStartTime(date);

		AvailRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().avail(request);
		return response;
    }
    
    /**
     * Add (post) and touractivity.
     * @param request
     * @return
     */
    @POST
    @Path("/avail/")
    public AvailRS postAvail(AvailRQ request) {
    	m_logger.info("Invoking add touractivity/avail (post)");
        AvailRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().avail(request);
        return response; //AvailRS.ok().type("application/xml").entity(touractivity).build();
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
    	m_logger.info("Invoking get touractivity/search (get), date range: " + startdate + ", to " + enddate);
        // Create a request
        SearchRQ request = new SearchRQ();
		request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
		DateTimePref dateTimePref = new DateTimePref();
		dateTimePref.setStart(startdate);
		dateTimePref.setEnd(enddate);
		request.setDateTimePref(dateTimePref);

		SearchRS response = null;
        if (getTourActivityService() != null)
        	response = getTourActivityService().search(request);
		return response;
    }
    
    /**
     * Search (post) and touractivity.
     * @param request
     * @return
     */
    @POST
    @Path("/search/")
    public SearchRS postSearch(SearchRQ request) {
    	m_logger.info("Invoking add touractivity/search (post)");
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

/**
 * TourActivity RESTful service
 */
package org.jibx.schema.org.opentravel._2011B.touractivity.ws.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base._Error;
import org.jibx.schema.org.opentravel._2011B.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2011B.touractivity.*;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.service.TourActivityServiceImpl;

@Path("/touractivityservice/")
public class TourActivityRestService {

	Map<String, AvailRQ> savedRequests = new HashMap<String, AvailRQ>();

    public TourActivityRestService() {
        init();
    }

    /**
     * Get a stored touractivity message by id
     * @param id
     * @return
     */
    @GET
    @Path("/avail/{id}/")
    @Produces("application/xml")
    public AvailRQ getAvail(@PathParam("id") String id) {
        System.out.println("----invoking get touractivity (get), AvailRQ id is: " + id);
        AvailRQ requestToReturn = savedRequests.get(id);
        if (requestToReturn == null) {
        	requestToReturn = new AvailRQ();
        	requestToReturn.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
//?        	requestToReturn.setEchoData("Error: Saved message with " + savedRequests.get(id) + " target does not exist.");
        }
        return requestToReturn;
    }
    
    /**
     * Update (put) and touractivity message.
     * @param request
     * @return
     */
    @PUT
    @Path("/avail/")
    public AvailRS putAvail(AvailRQ request) {
        System.out.println("----invoking update touractivity (put), AvailRQ name is: " + getKey(request));
        AvailRQ savedRequest = savedRequests.get(getKey(request));
        AvailRS response = getTourActivityService().avail(request);
        if (savedRequest != null) {
            savedRequests.put(getKey(request), request);
        } else {
            savedRequests.put(getKey(request), request);	// For now, insert new
        }

        return response;
    }

    /**
     * Add (put) and touractivity.
     * @param request
     * @return
     */
    @POST
    @Path("/avail/")
    public AvailRS postAvail(AvailRQ request) {
        System.out.println("----invoking add touractivity (post), AvailRQ name is: " + getKey(request));
        AvailRQ savedRequest = savedRequests.get(getKey(request));
        AvailRS response;
        if (savedRequest != null) {
        	response = new AvailRS();
        	response.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error: Did not add " + getKey(request) + ", target already exists.");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        } else {
            response = getTourActivityService().avail(request);
            savedRequests.put(getKey(request), request);
        }

        return response; //AvailRS.ok().type("application/xml").entity(touractivity).build();
    }

    @DELETE
    @Path("/avail/{id}/")
    public AvailRS deleteAvail(@PathParam("id") String id) {
        System.out.println("----invoking delete touractivity (delete), AvailRQ id is: " + id);
        AvailRQ savedRequest = savedRequests.get(id);

        AvailRS response = null;;
        if (savedRequest != null) {
            response = getTourActivityService().avail(savedRequest);
            savedRequests.remove(id);
        } else {
        	response = new AvailRS();
        	response.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error: Did not delete " + id + " target does not exist.");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        }

        return response;
    }

    final void init() {
        AvailRQ request = new AvailRQ();
//?        request.setEchoData("Hello");
        request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        request.getOTAPayloadStdAttributes().setTargetName("123");
        savedRequests.put(getKey(request), request);
    }

	public String getKey(AvailRQ request) {
		String key = request.getOTAPayloadStdAttributes().getTargetName();
		if (key == null)
			key = NONE;
		return key;
	}
	public static final String NONE = "0";

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

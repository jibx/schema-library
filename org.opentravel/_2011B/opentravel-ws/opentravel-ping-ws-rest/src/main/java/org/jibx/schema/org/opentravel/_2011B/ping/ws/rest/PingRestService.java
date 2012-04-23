/**
 * Ping RESTful service
 */
package org.jibx.schema.org.opentravel._2011B.ping.ws.rest;

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
import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;
import org.jibx.schema.org.opentravel._2011B.ping.ws.PingService;

@Path("/pingservice/")
public class PingRestService {

	Map<String, PingRQ> savedRequests = new HashMap<String, PingRQ>();

    public PingRestService() {
        init();
    }

    /**
     * Get a stored ping message by id
     * @param id
     * @return
     */
    @GET
    @Path("/ping/{id}/")
    @Produces("application/xml")
    public PingRQ getPingRQ(@PathParam("id") String id) {
        System.out.println("----invoking get ping (get), PingRQ id is: " + id);
        PingRQ requestToReturn = savedRequests.get(id);
        if (requestToReturn == null) {
        	requestToReturn = new PingRQ();
        	requestToReturn.setPayloadStdAttributes(BaseService.createStandardPayload());
        	requestToReturn.setEchoData("Error: Saved message with " + savedRequests.get(id) + " target does not exist.");
        }
        return requestToReturn;
    }
    
    /**
     * Update (put) and ping message.
     * @param request
     * @return
     */
    @PUT
    @Path("/ping/")
    public PingRS putPing(PingRQ request) {
        System.out.println("----invoking update ping (put), PingRQ name is: " + getKey(request));
        PingRQ savedRequest = savedRequests.get(getKey(request));
        PingRS response = getPingService().ping(request);
        if (savedRequest != null) {
            savedRequests.put(getKey(request), request);
        } else {
            savedRequests.put(getKey(request), request);	// For now, insert new
        }

        return response;
    }

    /**
     * Add (put) and ping.
     * @param request
     * @return
     */
    @POST
    @Path("/ping/")
    public PingRS postPing(PingRQ request) {
        System.out.println("----invoking add ping (post), PingRQ name is: " + getKey(request));
        PingRQ savedRequest = savedRequests.get(getKey(request));
        PingRS response;
        if (savedRequest != null) {
        	response = new PingRS();
        	response.setPayloadStdAttributes(BaseService.createStandardPayload());
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error: Did not add " + getKey(request) + ", target already exists.");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        } else {
            response = getPingService().ping(request);
            savedRequests.put(getKey(request), request);
        }

        return response; //PingRS.ok().type("application/xml").entity(ping).build();
    }

    @DELETE
    @Path("/ping/{id}/")
    public PingRS deletePing(@PathParam("id") String id) {
        System.out.println("----invoking delete ping (delete), PingRQ id is: " + id);
        PingRQ savedRequest = savedRequests.get(id);

        PingRS response = null;;
        if (savedRequest != null) {
            response = getPingService().ping(savedRequest);
            savedRequests.remove(id);
        } else {
        	response = new PingRS();
        	response.setPayloadStdAttributes(BaseService.createStandardPayload());
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
        PingRQ request = new PingRQ();
        request.setEchoData("Hello");
        request.setPayloadStdAttributes(BaseService.createStandardPayload());
        request.getPayloadStdAttributes().setTargetName("123");
        savedRequests.put(getKey(request), request);
    }

	public String getKey(PingRQ request) {
		String key = request.getPayloadStdAttributes().getTargetName();
		if (key == null)
			key = NONE;
		return key;
	}
	public static final String NONE = "0";

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

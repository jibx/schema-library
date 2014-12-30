/**
 * Ping RESTful service
 */
package org.jibx.schema.org.opentravel._2014B.ping.ws.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jibx.schema.org.opentravel._2014B.base.Errors;
import org.jibx.schema.org.opentravel._2014B.base._Error;
import org.jibx.schema.org.opentravel._2014B.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2014B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2014B.ping.PingRS;
import org.jibx.schema.org.opentravel._2014B.ping.ws.PingService;

@Path("/")
public class PingRestService {

	private Logger m_logger;
	
    public PingRestService() {
    	super();
        init();
    }

    public void init() {
        m_logger = Logger.getLogger("org.jibx.schema.org.opentravel");
    	m_logger.info("Starting PingRestService");
    }

    /**
     * Get a stored ping message by id
     * @param message
     * @return
     */
    @GET
    @Path("/ping/{message}/")
    @Produces("application/xml")
    public PingRQ getPingRQ(@PathParam("message") String message) {
    	m_logger.info("Invoking get ping (get), PingRQ message is: " + message);
        PingRQ response = new PingRQ();
    	response.setPayloadStdAttributes(BaseService.createStandardPayload());
    	response.setEchoData(message);
        return response;
    }
    
    /**
     * Ping a posted xml request.
     * @param request
     * @return
     */
    @POST
    @Path("/ping/")
    public PingRS postPing(PingRQ request) {
    	m_logger.info("Invoking add ping (post)");
        PingRS response = getPingService().ping(request);
        if (response == null) {
        	response = new PingRS();
        	response.setPayloadStdAttributes(BaseService.createStandardPayload());
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error: Did not return response");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        }

        return response; //PingRS.ok().type("application/xml").entity(ping).build();
    }

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
	 * @param
	 */
	public void setPingService(PingService pingService)
	{
		this.pingService = pingService;
	}
	protected PingService pingService = null;
}

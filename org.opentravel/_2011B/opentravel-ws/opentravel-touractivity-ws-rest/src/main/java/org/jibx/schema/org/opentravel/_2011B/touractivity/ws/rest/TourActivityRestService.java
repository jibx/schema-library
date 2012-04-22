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
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.impl.DefaultTourActivityService;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.service.TourActivityServiceImpl;
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;

@Path("/touractivityservice/")
public class TourActivityRestService
//	implements BundleActivator
{

    public TourActivityRestService() {
        init();
    }

    /**
     * Get a stored touractivity message by id
     * @param id
     * @return
     */
    @GET
    @Path("/avail/{id}/{date}/")
    @Produces("application/xml")
    public AvailRS getAvail(@PathParam("id") String id, @PathParam("date") String date) {
        System.out.println("----invoking get touractivity (get), tour id is: " + id + " date is: " + date);
        AvailRS requestToReturn = DefaultTourActivityService.createAvailRS(null);
    	requestToReturn.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        return requestToReturn;
    }
    
    /**
     * Add (post) and touractivity.
     * @param request
     * @return
     */
    @POST
    @Path("/avail/")
    public AvailRS postAvail(AvailRQ request) {
        System.out.println("----invoking add touractivity (post)");
        AvailRS response;
        response = getTourActivityService().avail(request);

        return response; //AvailRS.ok().type("application/xml").entity(touractivity).build();
    }

    final void init() {
        AvailRS request = new AvailRS();
//?        request.setEchoData("Hello");
        request.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        request.getOTAPayloadStdAttributes().setTargetName("123");
    }

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
/*
	public void start(BundleContext context) throws Exception {
        System.out.println("----osgi start");
		touractivityService = (TourActivityService)context.getServiceReference(TourActivityService.class);
	}

	public void stop(BundleContext context) throws Exception {
	}*/
}

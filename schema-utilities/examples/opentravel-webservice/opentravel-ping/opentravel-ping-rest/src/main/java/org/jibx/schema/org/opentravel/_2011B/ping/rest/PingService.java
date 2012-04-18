/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jibx.schema.org.opentravel._2011B.ping.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2011B.base.Success;
import org.jibx.schema.org.opentravel._2011B.base._Error;
import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS.Sequence;
import org.joda.time.DateTime;

@Path("/pingservice/")
public class PingService {

	Map<String, PingRQ> savedRequests = new HashMap<String, PingRQ>();

    public PingService() {
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
        	requestToReturn.setPayloadStdAttributes(this.createStandardPayload());
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
        PingRS response = this.ping(request);
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
        	response.setPayloadStdAttributes(this.createStandardPayload());
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error: Did not add " + getKey(request) + ", target already exists.");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        } else {
            response = this.ping(request);
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
            response = this.ping(savedRequest);
            savedRequests.remove(id);
        } else {
        	response = new PingRS();
        	response.setPayloadStdAttributes(this.createStandardPayload());
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
        request.setPayloadStdAttributes(this.createStandardPayload());
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

	public PingRS ping(PingRQ request) {

		PingRS response = new PingRS();
		response.setPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
        if (request.getEchoData() == null || request.getEchoData().length() == 0) {
        	Errors errors = new Errors();
        	_Error error = new _Error();
        	error.setString("Error, empty echo data");
        	error.setType("10");	// Required field missing
        	errors.addError(error);
        	response.setErrors(errors);
        	return response;
        }

        Sequence sequence = new Sequence();
        sequence.setSuccess(new Success());
        sequence.setEchoData(request.getEchoData());
        response.addSuccess(sequence);
		
        return response;
	}
    /**
     * Shared method to set up a default payload.
     * @return The payload
     */
    public OTAPayloadStdAttributes createStandardPayload()
    {
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        DateTime timeStamp = new DateTime();
        payloadStdAttributes.setTimeStamp(timeStamp);
        Float version = new Float(1.00);
        payloadStdAttributes.setVersion(version);
        payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.PRODUCTION);
        return payloadStdAttributes;
    }
    /**
     * Shared method to move payload data from the request to the response.
     * @return The payload
     */
    public void movePayloadData(OTAPayloadStdAttributes requestStdAttributes, OTAPayloadStdAttributes responseStdAttributes)
    {
    	responseStdAttributes.setAltLangID(requestStdAttributes.getAltLangID());
    	responseStdAttributes.setCorrelationID(requestStdAttributes.getCorrelationID());
    	responseStdAttributes.setEchoToken(requestStdAttributes.getEchoToken());
    	responseStdAttributes.setPrimaryLangID(requestStdAttributes.getPrimaryLangID());
    	responseStdAttributes.setRetransmissionIndicator(requestStdAttributes.getRetransmissionIndicator());
    	responseStdAttributes.setSequenceNmbr(requestStdAttributes.getSequenceNmbr());
    	if (responseStdAttributes.getTarget() == null)
    		responseStdAttributes.setTarget(requestStdAttributes.getTarget());
    	responseStdAttributes.setTargetName(requestStdAttributes.getTargetName());
    	if (responseStdAttributes.getTimeStamp() == null)
    		responseStdAttributes.setTimeStamp(requestStdAttributes.getTimeStamp());
    	responseStdAttributes.setTransactionIdentifier(requestStdAttributes.getTransactionIdentifier());
    	responseStdAttributes.setTransactionStatusCode(requestStdAttributes.getTransactionStatusCode());
    	if (responseStdAttributes.getVersion() == null)
    		responseStdAttributes.setVersion(requestStdAttributes.getVersion());
    }
}

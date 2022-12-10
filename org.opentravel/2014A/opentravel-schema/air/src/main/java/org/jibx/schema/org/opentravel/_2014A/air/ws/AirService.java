/*
 * Copyright (c) 2010, Dennis M. Sosnoski. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of
 * JiBX nor the names of its contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jibx.schema.org.opentravel._2014A.air.ws;

import org.jibx.schema.org.opentravel._2014A.air.*;

/**
 * OTA Air web service definition. This defines a method for each request/response pair in the Air group.
 */
public interface AirService
{
    /**
     * Request flight availability for a city pair on a specific date for a specific number and type of passengers.
     */
	public AvailRS avail(AvailRQ req);
    
    /**
     * Book a specific itinerary for one or more identified passengers.
     */
	public BookRS book(BookRQ req);
    
    /**
     * Modify a previously-booked itinerary for one or more identified passengers.
     */
	public BookRS bookModify(BookModifyRQ req);
    
    /**
     * Submit check-in information for self-service channels (kiosks, web and mobile).
     */
	public CheckInRS checkin(CheckInRQ req);
    
    /**
     * Request that a company responsible for ticket fulfillment, such as a GDS, issue an electronic ticket and/or
     * automated miscellaneous charge order (MCO).
     */
	public DemandTicketRS demandTicket(DemandTicketRQ req);
    
    /**
     * Get additional flight details for each leg of a flight (e.g. journey time, meal service).
     */
	public DetailsRS details(DetailsRQ req);
    
    /**
     * Display fares between a given city pair.
     */
	public FareDisplayRS fareDisplay(FareDisplayRQ req);
    
    /**
     * Retrieve real-time flight departure, arrival and gate information for a particular flight, including actual as
     * well as scheduled departure and arrival times.
     */
	public FlifoRS flifo(FlifoRQ req);
    
    /**
     * Get priced itinerary options for flights between specific city pairs on specific dates for specific numbers and
     * types of passengers.
     */
	public LowFareSearchRS lowFareSearch(LowFareSearchRQ req);
    
    /**
     * Get pricing information for specific flights on specific dates for a specific number and type of passengers.
     */
	public PriceRS price(PriceRQ req);
    
    /**
     * Get text rules for a specific fare class code for an airline and city pair on a specific date.
     */
	public RulesRS rules(RulesRQ req);
    
    /**
     * Display flight schedule between a given city pair.
     */
	public ScheduleRS schedule(ScheduleRQ req);
    
    /**
     * Get seat map details for one or more flight segments for specific dates.
     */
	public SeatMapRS seatMap(SeatMapRQ req);
}
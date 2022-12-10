package org.jibx.schema.org.opentravel.x2012B.air.ws.impl;

import org.jibx.schema.org.opentravel.x2012B.base.ws.BaseService;
import org.jibx.schema.org.opentravel.x2012B.air.ws.AirService;
import org.jibx.schema.org.opentravel.x2012B.air.*;


/**
 * Skeleton ping service implementation.
 * @author don
 *
 */
public abstract class DefaultAirService extends BaseService
	implements AirService
{

	/**
	 * Constructor
	 */
	public DefaultAirService()
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
     * Request flight availability for a city pair on a specific date for a specific number and type of passengers.
     */
	public AvailRS avail(AvailRQ req)
	{
		return null;
	}
    
    /**
     * Book a specific itinerary for one or more identified passengers.
     */
	public BookRS book(BookRQ req)
	{
		return null;
	}
    
    /**
     * Modify a previously-booked itinerary for one or more identified passengers.
     */
	public BookRS bookModify(BookModifyRQ req)
	{
		return null;
	}
    
    /**
     * Submit check-in information for self-service channels (kiosks, web and mobile).
     */
	public CheckInRS checkin(CheckInRQ req)
	{
		return null;
	}
    
    /**
     * Request that a company responsible for ticket fulfillment, such as a GDS, issue an electronic ticket and/or
     * automated miscellaneous charge order (MCO).
     */
	public DemandTicketRS demandTicket(DemandTicketRQ req)
	{
		return null;
	}
    
    /**
     * Get additional flight details for each leg of a flight (e.g. journey time, meal service).
     */
	public DetailsRS details(DetailsRQ req)
	{
		return null;
	}
    
    /**
     * Display fares between a given city pair.
     */
	public FareDisplayRS fareDisplay(FareDisplayRQ req)
	{
		return null;
	}
    
    /**
     * Retrieve real-time flight departure, arrival and gate information for a particular flight, including actual as
     * well as scheduled departure and arrival times.
     */
	public FlifoRS flifo(FlifoRQ req)
	{
		return null;
	}
    
    /**
     * Get priced itinerary options for flights between specific city pairs on specific dates for specific numbers and
     * types of passengers.
     */
	public LowFareSearchRS lowFareSearch(LowFareSearchRQ req)
	{
		return null;
	}
    
    /**
     * Get pricing information for specific flights on specific dates for a specific number and type of passengers.
     */
	public PriceRS price(PriceRQ req)
	{
		return null;
	}
    
    /**
     * Get text rules for a specific fare class code for an airline and city pair on a specific date.
     */
	public RulesRS rules(RulesRQ req)
	{
		return null;
	}
    
    /**
     * Display flight schedule between a given city pair.
     */
	public ScheduleRS schedule(ScheduleRQ req)
	{
		return null;
	}
    
    /**
     * Get seat map details for one or more flight segments for specific dates.
     */
	public SeatMapRS seatMap(SeatMapRQ req)
	{
		return null;
	}
}
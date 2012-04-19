package org.jibx.schema.org.opentravel._2011B.cruise.ws.impl;

import org.jibx.schema.org.opentravel._2011B.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2011B.cruise.ws.CruiseService;
import org.jibx.schema.org.opentravel._2011B.cruise.*;

/**
 * Skeleton cruise service implementation.
 * @author don
 *
 */
public abstract class AbstractCruiseService extends BaseService
	implements CruiseService
{

	/**
	 * Search for a sailing.
	 */
	public SailAvailRQ sailAvail(SailAvailRQ request)
	{
		return null;
	}
	
	/**
	 * Request available fares for the selected voyage.
	 */
	public FareAvailRQ fareAvail(FareAvailRQ request)
	{
		return null;
	}
	
	/**
	 * Request the available categories.
	 */
	public CategoryAvailRS categoryAvail(CategoryAvailRQ request)
	{
		return null;
	}
	
	/**
	 * After the category has been selected, and any time prior to creating the booking, pricing can be requested.
	 */
	public PriceBookingRS priceBooking(PriceBookingRQ request)
	{
		return null;
	}
	
	/**
	 * Request a list of available cabins in the selected category.
	 */
	public CabinAvailRS cabinAvail(CabinAvailRQ request)
	{
		return null;
	}
	
	/**
	 * Place one or more cabins on hold.
	 */
	public CabinHoldRS cabinHold(CabinHoldRQ request)
	{
		return null;
	}
	
	/**
	 * Release the specific cabin(s) currently on hold.
	 */
	public CabinUnholdRS cabinUnhold(CabinUnholdRQ request)
	{
		return null;
	}
	
	/**
	 * The traveler creates a booking.
	 */
	public BookRS book(BookRQ request)
	{
		return null;
	}
	
	/**
	 * Request the penalties for both the booking and the individual travelers.
	 */
	public CancellationPricingRS cancellationPricing(CancellationPricingRQ request)
	{
		return null;
	}
	
	/**
	 * Requests for dining options.
	 */
	public DiningAvailRS diningAvail(DiningAvailRQ request)
	{
		return null;
	}
	
	/**
	 * The FastSellRQ message is a multipurpose request.
	 */
	public void fastSell(FastSellRQ request)
	{
	}

	/**
	 * allows the sharing of miscellaneous structured cruise information.
	 */
	public InfoRS info(InfoRQ request)
	{
		return null;
	}
	
	/**
	 * Request the detailed itinerary.
	 */
	public ItineraryDescRS itineraryDesc(ItineraryDescRQ request)
	{
		return null;
	}
	
	/**
	 * Submit payments for an existing reservation without first retrieving the reservation.
	 */
	public PaymentRS payment(PaymentRQ request)
	{
		return null;
	}
	
	/**
	 * request information about the pre-cruise, post-cruise, or the inclusive-cruise packages associated with a sailing.
	 */
	public PkgAvailRS pkgAvail(PkgAvailRQ request)
	{
		return null;
	}
	
	/**
	 * Push unsolicited update messages.
	 */
	public PNRUpdateNotifRS pnrUpdateNotif(PNRUpdateNotifRQ request)
	{
		return null;
	}
	
	/**
	 * Request information about shore excursions.
	 */
	public ShorexAvailRS shorexAvail(ShorexAvailRQ request)
	{
		return null;
	}
	
	/**
	 * Requests about special services.
	 */
	public SpecialServiceAvailRS specialServiceAvail(SpecialServiceAvailRQ request)
	{
		return null;
	}
	
}

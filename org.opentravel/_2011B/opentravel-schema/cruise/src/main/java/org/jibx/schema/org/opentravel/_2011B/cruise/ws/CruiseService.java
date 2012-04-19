package org.jibx.schema.org.opentravel._2011B.cruise.ws;

import org.jibx.schema.org.opentravel._2011B.cruise.*;

public interface CruiseService {

	/**
	 * Search for a sailing.
	 */
	public SailAvailRQ sailAvail(SailAvailRQ request);
	
	/**
	 * Request available fares for the selected voyage.
	 */
	public FareAvailRQ fareAvail(FareAvailRQ request);
	
	/**
	 * Request the available categories.
	 */
	public CategoryAvailRS categoryAvail(CategoryAvailRQ request);
	
	/**
	 * After the category has been selected, and any time prior to creating the booking, pricing can be requested.
	 */
	public PriceBookingRS priceBooking(PriceBookingRQ request);
	
	/**
	 * Request a list of available cabins in the selected category.
	 */
	public CabinAvailRS cabinAvail(CabinAvailRQ request);
	
	/**
	 * Place one or more cabins on hold.
	 */
	public CabinHoldRS cabinHold(CabinHoldRQ request);
	
	/**
	 * Release the specific cabin(s) currently on hold.
	 */
	public CabinUnholdRS cabinUnhold(CabinUnholdRQ request);
	
	/**
	 * The traveler creates a booking.
	 */
	public BookRS book(BookRQ request);
	
	/**
	 * Request the penalties for both the booking and the individual travelers.
	 */
	public CancellationPricingRS cancellationPricing(CancellationPricingRQ request);
	
	/**
	 * Requests for dining options.
	 */
	public DiningAvailRS diningAvail(DiningAvailRQ request);
	
	/**
	 * The FastSellRQ message is a multipurpose request.
	 */
	public void fastSell(FastSellRQ request);

	/**
	 * allows the sharing of miscellaneous structured cruise information.
	 */
	public InfoRS info(InfoRQ request);
	
	/**
	 * Request the detailed itinerary.
	 */
	public ItineraryDescRS itineraryDesc(ItineraryDescRQ request);
	
	/**
	 * Submit payments for an existing reservation without first retrieving the reservation.
	 */
	public PaymentRS payment(PaymentRQ request);
	
	/**
	 * request information about the pre-cruise, post-cruise, or the inclusive-cruise packages associated with a sailing.
	 */
	public PkgAvailRS pkgAvail(PkgAvailRQ request);
	
	/**
	 * Push unsolicited update messages.
	 */
	public PNRUpdateNotifRS pnrUpdateNotif(PNRUpdateNotifRQ request);
	
	/**
	 * Request information about shore excursions.
	 */
	public ShorexAvailRS shorexAvail(ShorexAvailRQ request);
	
	/**
	 * Requests about special services.
	 */
	public SpecialServiceAvailRS specialServiceAvail(SpecialServiceAvailRQ request);
	
}

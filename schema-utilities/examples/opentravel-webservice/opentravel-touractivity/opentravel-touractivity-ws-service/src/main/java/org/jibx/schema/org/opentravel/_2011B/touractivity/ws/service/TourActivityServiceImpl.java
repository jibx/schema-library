package org.jibx.schema.org.opentravel._2011B.touractivity.ws.service;

import java.util.Calendar;
import java.util.Date;

import org.jibx.schema.org.opentravel._2011B.base.AgeQualifier;
import org.jibx.schema.org.opentravel._2011B.base.AgeQualifyingEnum;
import org.jibx.schema.org.opentravel._2011B.base.CurrencyAmountGroup;
import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.Success;
import org.jibx.schema.org.opentravel._2011B.base.touractivity.TourActivityCharge;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRQ;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRS;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRS.TourActivityInfo;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRS.TourActivityInfo.Pricing;
import org.jibx.schema.org.opentravel._2011B.touractivity.AvailRS.TourActivityInfo.Pricing.ParticipantCategory;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel._2011B.touractivity.ws.impl.DefaultTourActivityService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Example web services server for the opentravel TourActivity Avail message pair.
 * @author Don Corley <don@tourgeek.com>
 */
public class TourActivityServiceImpl extends DefaultTourActivityService
	implements TourActivityService, BundleActivator
{

	/**
	 * Constructor
	 */
	public TourActivityServiceImpl()
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

	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(TourActivityService.class.getName(), this, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		// Unregisters automagically
	}

	/**
	 * Service the avail request.
	 * @param request
	 * @return
	 */
	public AvailRS avail(AvailRQ request)
	{
		String tourActivityID = null;
		if (request.getTourActivityBasicInfo(0) != null)
			tourActivityID = request.getTourActivityBasicInfo(0).getTourActivityID();
		Date date = null;
		if (request.getSchedule(0) != null)
			date = convertYMDToDate(request.getSchedule(0).getStartTime(0));

		AvailRS response = createAvailRS(request);
		TourActivityInfo tourActivityInfo = createAndAddTourActivityInfo(request, response, 0);
		if ((request.getSchedule(0) != null)
				&& (request.getSchedule(0).getDatePeriodGroup() != null))
			{	// Move start date
				String sourceDate = request.getSchedule(0).getDatePeriodGroup().getStartPeriod();
				setTourActivityInfoStartDate(tourActivityInfo, sourceDate);
			}
		if ((tourActivityID == null) || (date == null)) {	// Error, date and tour are required
        	Errors errors = addError(null, "Error, you must supply a tour code and date", null);
        	response.setErrors(errors);
        	return response;
        }

		// Connect to my (fake) back-end system where I can get the requested information
		MyTourInfo myTourInfo = new MyTourInfo(tourActivityID);
		int availability = myTourInfo.getAvailability(date);
		float price = myTourInfo.getPrice(date);
		// Move the information to my return message
		tourActivityInfo.setScheduleAvailableQty(availability);
		addPricing(tourActivityInfo, AgeQualifyingEnum.ADULT, price);
		response.setSuccess(new Success());

		return response;
	}
	
	/**
	 * Add this price/category combination to the touractivityinfo element.
	 * @param tourActivityInfo
	 * @param ageQualifyingEnum
	 * @param price
	 */
	public void addPricing(TourActivityInfo tourActivityInfo, AgeQualifyingEnum ageQualifyingEnum, float price)
	{
		Pricing pricing = new Pricing();
		tourActivityInfo.setPricing(pricing);
		ParticipantCategory participantCategory = new ParticipantCategory();
		pricing.addParticipantCategory(participantCategory);
		AgeQualifier ageQualifier = new AgeQualifier();
		participantCategory.setQualifierInfo(ageQualifier);
		ageQualifier.setAgeQualifyingEnum(ageQualifyingEnum);
		TourActivityCharge tourActivityCharge = new TourActivityCharge();
		participantCategory.setPrice(tourActivityCharge);
		CurrencyAmountGroup currencyAmountGroup = new CurrencyAmountGroup();
		tourActivityCharge.setCurrencyAmountGroup(currencyAmountGroup);
		currencyAmountGroup.setAmount(price);
	}
		
	/**
	 * This mock object represents your back-end system.
	 * The actual implementation would do a SQL query on your database or
	 * more likely do a web service call to your system.
	 * 
	 * This fake implementation has two tours:
	 * cityss - Price $82.50, 5 spaces available every day but Thursday.
	 * airxfer - Price $10.00, 10 spaces available every day.
	 */
	public class MyTourInfo
	{
		public static final String CITY_SIGHTSEEING = "cityss";
		public static final String AIRPORT_TRANSFER = "airxfer";
		
		String tourActivityID;
		public MyTourInfo(String tourActivityID)
		{
			super();
			this.tourActivityID = tourActivityID;
		}
		public int getAvailability(Date date)
		{
			if (AIRPORT_TRANSFER.equalsIgnoreCase(tourActivityID))
				return 10;
			if (CITY_SIGHTSEEING.equalsIgnoreCase(tourActivityID))
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY)
					return 5;
			}
			return 0;
		}
	
		public float getPrice(Date date)
		{
			if (CITY_SIGHTSEEING.equalsIgnoreCase(tourActivityID))
				return 82.50f;
			if (AIRPORT_TRANSFER.equalsIgnoreCase(tourActivityID))
				return 55;
			return 0;
		}
	}
}

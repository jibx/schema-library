package org.jibx.schema.org.opentravel.x2013A.touractivity.ws.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jibx.schema.org.opentravel.x2013A.base.AgeQualifier;
import org.jibx.schema.org.opentravel.x2013A.base.AgeQualifyingEnum;
import org.jibx.schema.org.opentravel.x2013A.base.CurrencyAmountGroup;
import org.jibx.schema.org.opentravel.x2013A.base.Errors;
import org.jibx.schema.org.opentravel.x2013A.base.Success;
import org.jibx.schema.org.opentravel.x2013A.base.touractivity.TourActivityCharge;
import org.jibx.schema.org.opentravel.x2013A.base.touractivity.TourActivityDescription;
import org.jibx.schema.org.opentravel.x2013A.base.touractivity.TourActivityID;
import org.jibx.schema.org.opentravel.x2013A.touractivity.AvailRQ;
import org.jibx.schema.org.opentravel.x2013A.touractivity.AvailRS;
import org.jibx.schema.org.opentravel.x2013A.touractivity.AvailRS.TourActivityInfo;
import org.jibx.schema.org.opentravel.x2013A.touractivity.AvailRS.TourActivityInfo.Pricing;
import org.jibx.schema.org.opentravel.x2013A.touractivity.AvailRS.TourActivityInfo.Pricing.ParticipantCategory;
import org.jibx.schema.org.opentravel.x2013A.touractivity.SearchRQ;
import org.jibx.schema.org.opentravel.x2013A.touractivity.SearchRQ.DateTimePref;
import org.jibx.schema.org.opentravel.x2013A.touractivity.SearchRS;
import org.jibx.schema.org.opentravel.x2013A.touractivity.ws.TourActivityService;
import org.jibx.schema.org.opentravel.x2013A.touractivity.ws.impl.DefaultTourActivityService;

/**
 * Example web services server for the opentravel TourActivity Avail message pair.
 * NOTE: This is an example framework implementation. Obviously, you would access your
 * database for tour information instead of hard-coding your access code.
 * @author Don Corley <don@tourgeek.com>
 */
public class TourActivityServiceImpl extends DefaultTourActivityService
	implements TourActivityService
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

	/**
	 * Service the search request.
	 * @param request
	 * @return
	 */
	public SearchRS search(SearchRQ request)
	{
		Date startDate = null;
		Date endDate = null;
		if (request.getDateTimePref() != null)
		{
			DateTimePref dateTimePref = request.getDateTimePref();
			if (dateTimePref != null)
			{
				startDate = convertYMDToDate(dateTimePref.getStart());
				endDate = convertYMDToDate(dateTimePref.getEnd());
				if (endDate == null)
					if (dateTimePref.getDuration() != null)
						;	// + Add duration to start date
			}
		}

		SearchRS response = createSearchRS(request);

		for (TourInfo tourInfo : mapTourInfo.values())
		{
			if (tourInfo.isDateValid(startDate, endDate))
				response.addTourActivityInfo(createTourActivityInfo(tourInfo));
		}
		response.setSuccess(new Success());

		return response;
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
		TourInfo myTourInfo = mapTourInfo.get(tourActivityID);
		if (myTourInfo == null)
			myTourInfo = NO_TOUR;
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
	 * Create a search response tour object for this tour.
	 * @param tourInfo
	 * @return
	 */
	public SearchRS.TourActivityInfo createTourActivityInfo(TourInfo tourInfo)
	{
		SearchRS.TourActivityInfo tourActivityInfo = new SearchRS.TourActivityInfo();
		TourActivityID basicInfo = new TourActivityID();
		basicInfo.setTourActivityID(tourInfo.getTourId());
		tourActivityInfo.setBasicInfo(basicInfo);
		TourActivityDescription description = new TourActivityDescription();
		description.setShortDescription(tourInfo.getDescription());
		tourActivityInfo.setDescription(description);
		SearchRS.TourActivityInfo.Pricing pricing = new SearchRS.TourActivityInfo.Pricing();
		pricing.setMinPrice(tourInfo.getPrice(null));
		pricing.setMaxPrice(tourInfo.getPrice(null));
		tourActivityInfo.setPricing(pricing);
		return tourActivityInfo;
	}
		
	/**
	 * These mock objects represent your back-end system.
	 * The actual implementation would do a SQL query on your database or
	 * more likely do a web service call to your system.
	 * 
	 * This fake implementation has several tours.
	 * Take a look at the static initialization code.
	 */
	static class TourInfo
	{
		String tourActivityID;
		String description;
		int[] availability;
		float price;
		Date startDate;
		Date endDate;
		
		public TourInfo(String tourActivityID, String description, Date startDate, Date endDate, int[] availability, float price)
		{
			super();
			this.tourActivityID = tourActivityID;
			this.description = description;
			this.availability = availability;
			this.price = price;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		public String getTourId()
		{
			return tourActivityID;
		}
		public String getDescription()
		{
			return description;
		}
		public int getAvailability(Date date)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return availability[cal.get(Calendar.DAY_OF_WEEK) - 1];
		}
		public float getPrice(Date date)
		{
			return price;
		}
		public boolean isDateValid(Date startDate, Date endDate)
		{
			if (startDate != null)
				if (this.endDate != null)
					if (startDate.getTime() > this.endDate.getTime())
						return false;
			if (endDate != null)
				if (this.startDate != null)
					if (endDate.getTime() < this.startDate.getTime())
						return false;
			if ((startDate != null) && (endDate != null))
				if (startDate.equals(endDate))
					if (getAvailability(startDate) == 0)
						return false;
			return true;	// Valid date
		}
	}
	static int[] NOT_TUESDAY = {5, 5, 5, 0, 5, 5, 5};
	static int[] EVERY_DAY = {8, 8, 8, 8, 8, 8, 8};
	static int[] NO_AVAIL = {0, 0, 0, 0, 0, 0, 0};
	public static Map<String,TourInfo> mapTourInfo = new HashMap<String,TourInfo>();
	static {
		Date startDate = null;
		Date endDate = null;
		mapTourInfo.put("airxfer", new TourInfo("airxfer", "Airport Transfer", startDate, endDate, EVERY_DAY, 82.50f));
		startDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, 180);
		endDate = cal.getTime();
		mapTourInfo.put("cityss", new TourInfo("cityss", "City Sightseeing", startDate, endDate, NOT_TUESDAY, 55));
		startDate = null;
		endDate = null;
		mapTourInfo.put("hoponoff", new TourInfo("hoponoff", "Hop on-Hop off Bus", startDate, endDate, EVERY_DAY, 40));
		mapTourInfo.put("river", new TourInfo("river", "River Tour", startDate, endDate, NOT_TUESDAY, 95));
		mapTourInfo.put("canyon", new TourInfo("canyon", "Canyon Tour", startDate, endDate, EVERY_DAY, 85));
		mapTourInfo.put("suburb", new TourInfo("suburb", "Suburb Tour", startDate, endDate, EVERY_DAY, 60));
		startDate = new Date();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, 90);
		endDate = cal.getTime();
		mapTourInfo.put("rural", new TourInfo("rural", "Rural Tour", startDate, endDate, EVERY_DAY, 155));
		startDate = new Date();
		cal.setTime(startDate);
		if (cal.get(Calendar.MONTH) >= 9)
			cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DATE, 22);
		startDate = cal.getTime();
		cal.add(Calendar.MONTH, 3);
		endDate = cal.getTime();
		mapTourInfo.put("fall", new TourInfo("fall", "Fall Tour", startDate, endDate, EVERY_DAY, 145));
	}
	TourInfo NO_TOUR = new TourInfo("", "", null, null, NO_AVAIL, 0);
}

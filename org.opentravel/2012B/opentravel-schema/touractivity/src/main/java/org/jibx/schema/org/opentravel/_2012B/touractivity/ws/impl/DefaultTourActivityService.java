package org.jibx.schema.org.opentravel._2012B.touractivity.ws.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jibx.schema.org.opentravel._2012B.touractivity.AvailRS.TourActivityInfo;
import org.jibx.schema.org.opentravel._2012B.touractivity.AvailRS.TourActivityInfo.Summary;
import org.jibx.schema.org.opentravel._2012B.touractivity.ws.*;
import org.jibx.schema.org.opentravel._2012B.base.DateTimeSpanGroup;
import org.jibx.schema.org.opentravel._2012B.base.touractivity.TourActivityID;
import org.jibx.schema.org.opentravel._2012B.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2012B.misc.ReadRQ;
import org.jibx.schema.org.opentravel._2012B.touractivity.*;

public abstract class DefaultTourActivityService extends BaseService
implements TourActivityService
{

	/**
	 * Service the avail request.
	 * @param request
	 * @return
	 */
	public AvailRS avail(AvailRQ request)
	{
		return null;	// Need to override with implementation
	}

	/**
	 * Service the book request.
	 * @param request
	 * @return
	 */
	public BookRS book(BookRQ request)
	{
		return null;	// Need to override with implementation
	}

	/**
	 * Service the cancel request.
	 * @param request
	 * @return
	 */
	public CancelRS cancel(CancelRQ request)
	{
		return null;	// Need to override with implementation
	}

	/**
	 * Service the modify request.
	 * @param request
	 * @return
	 */
	public BookRS modify(ModifyRQ request)
	{
		return null;	// Need to override with implementation
	}

	/**
	 * Service the resRetrieve request.
	 * @param request
	 * @return
	 */
	public ResRetrieveRS resRetrieve(ReadRQ request)
	{
		return null;	// Need to override with implementation
	}

	/**
	 * Service the search request.
	 * @param request
	 * @return
	 */
	public SearchRS search(SearchRQ request)
	{
		return null;	// Need to override with implementation
	}
	
	/**
	 * Create an empty availability response.
	 * @param request
	 * @return
	 */
	public static AvailRS createAvailRS(AvailRQ request)
	{
		AvailRS response = new AvailRS();
		response.setOTAPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
		return response;
	}
	/**
	 * Add a matching TourActivityInfo element to the response.
	 * @param request
	 * @param response
	 * @param index
	 * @return
	 */
	public static TourActivityInfo createAndAddTourActivityInfo(AvailRQ request, AvailRS response, int index)
	{
		String tourActivityID = null;
		if (request.getTourActivityBasicInfo(index) != null)
			tourActivityID = request.getTourActivityBasicInfo(index).getTourActivityID();
		if (tourActivityID != null)
		{	// Move tour code
			TourActivityInfo tourActivityInfo = new TourActivityInfo();
			response.addTourActivityInfo(tourActivityInfo);
			TourActivityID basicInfo = new TourActivityID();
			tourActivityInfo.setBasicInfo(basicInfo);
			basicInfo.setTourActivityID(tourActivityID);
			return tourActivityInfo;
		}
		return null;	// Error
	}
	
	/**
	 * Set the start date.
	 * @param tourActivityInfo
	 * @param date
	 */
	public static void setTourActivityInfoStartDate(TourActivityInfo tourActivityInfo, String date)
	{
		Summary summary = tourActivityInfo.getSummary();
		if (summary == null)
			tourActivityInfo.setSummary(summary = new Summary());
		DateTimeSpanGroup dateTimeSpanGroup = summary.getDateTimeSpanGroup();
		if (dateTimeSpanGroup == null)
			summary.setDateTimeSpanGroup(dateTimeSpanGroup = new DateTimeSpanGroup());
		dateTimeSpanGroup.setStart(date);
	}
		
	/**
	 * Create an empty availability response.
	 * @param request
	 * @return
	 */
	public static SearchRS createSearchRS(SearchRQ request)
	{
		SearchRS response = new SearchRS();
		response.setOTAPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
		return response;
	}
}

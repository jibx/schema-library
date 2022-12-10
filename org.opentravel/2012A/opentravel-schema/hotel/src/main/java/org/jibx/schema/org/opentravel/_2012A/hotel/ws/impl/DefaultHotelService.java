package org.jibx.schema.org.opentravel._2012A.hotel.ws.impl;

import org.jibx.schema.org.opentravel._2012A.hotel.ws.*;
import org.jibx.schema.org.opentravel._2012A.hotel.*;
import org.jibx.schema.org.opentravel._2012A.base.ws.*;
import org.joda.time.DateTime;

/**
 * Skeleton hotel service implementation.
 * @author don
 *
 */
public abstract class DefaultHotelService extends BaseService
	implements HotelService
{

	/**
	 * Constructor
	 */
	public DefaultHotelService()
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
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailRS avail(AvailRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailGetRS availGet(AvailGetRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailNotifRS availNotif(AvailNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookingRuleRS bookingRule(BookingRuleRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookingRuleNotifRS bookingRuleNotif(BookingRuleNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CacheChangeRS cacheChange(CacheChangeRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CacheChangeNotifRS cacheChangeNotif(CacheChangeNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CommNotifRS commNotif(CommNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public DescriptiveContentNotifRS descriptiveContentNotif(DescriptiveContentNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public DescriptiveInfoRS descriptiveInfo(DescriptiveInfoRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public EventRS event(EventRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public GetMsgRS getMsg(GetMsgRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvAdjustRS invAdjust(InvAdjustRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvBlockRS invBlock(InvBlockRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvBlockNotifRS invBlockNotif(InvBlockNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvCountRS invCount(InvCountRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvCountNotifRS invCountNotif(InvCountNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvNotifRS invNotif(InvNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvSyncRS invSync(InvSyncRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	//public PostEventReportRS postEventReport(PostEventReportRQ request)
	//{
	//	return null;
	//}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PostEventNotifRS postEventNotif(PostEventNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RateAmountNotifRS rateAmountNotif(RateAmountNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RatePlanRS ratePlan(RatePlanRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RatePlanNotifRS ratePlanNotif(RatePlanNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResModifyRS resModify(ResModifyRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public static ResModifyRS createResModifyRS(ResModifyRQ request)
	{
		ResModifyRS response = new ResModifyRS();
		response.setOTAPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
		return response;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResModifyNotifRS resModifyNotif(ResModifyNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResRS res(ResRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public static ResRS createResRS(ResRQ request)
	{
		ResRS response = new ResRS();
		response.setOTAPayloadStdAttributes(createStandardPayload());
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
		return response;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPMeetingRS rFPMeeting(RFPMeetingRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPMeetingNotifRS rFPMeetingNotif(RFPMeetingNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPTransientRS rFPTransient(RFPTransientRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RoomListRS roomList(RoomListRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public SearchRS search(SearchRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StatsRS stats(StatsRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StatsNotifRS statsNotif(StatsNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StayInfoNotifRS stayInfoNotif(StayInfoNotifRQ request)
	{
		return null;
	}

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public SummaryNotifRS summaryNotif(SummaryNotifRQ request)
	{
		return null;
	}

}

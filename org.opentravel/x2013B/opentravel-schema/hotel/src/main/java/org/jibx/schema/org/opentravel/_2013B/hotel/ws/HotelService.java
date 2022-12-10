package org.jibx.schema.org.opentravel.x2013B.hotel.ws;

import org.jibx.schema.org.opentravel.x2013B.hotel.*;

public interface HotelService {

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailRS avail(AvailRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailGetRS availGet(AvailGetRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public AvailNotifRS availNotif(AvailNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookingRuleRS bookingRule(BookingRuleRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public BookingRuleNotifRS bookingRuleNotif(BookingRuleNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CacheChangeRS cacheChange(CacheChangeRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CacheChangeNotifRS cacheChangeNotif(CacheChangeNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public CommNotifRS commNotif(CommNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public DescriptiveContentNotifRS descriptiveContentNotif(DescriptiveContentNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public DescriptiveInfoRS descriptiveInfo(DescriptiveInfoRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public EventRS event(EventRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public GetMsgRS getMsg(GetMsgRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvAdjustRS invAdjust(InvAdjustRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvBlockRS invBlock(InvBlockRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvBlockNotifRS invBlockNotif(InvBlockNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvCountRS invCount(InvCountRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvCountNotifRS invCountNotif(InvCountNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvNotifRS invNotif(InvNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public InvSyncRS invSync(InvSyncRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	//public PostEventReportRS postEventReport(PostEventReportRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PostEventNotifRS postEventNotif(PostEventNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RateAmountNotifRS rateAmountNotif(RateAmountNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RatePlanRS ratePlan(RatePlanRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RatePlanNotifRS ratePlanNotif(RatePlanNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResModifyRS resModify(ResModifyRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResModifyNotifRS resModifyNotif(ResModifyNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public ResRS res(ResRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPMeetingRS rFPMeeting(RFPMeetingRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPMeetingNotifRS rFPMeetingNotif(RFPMeetingNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RFPTransientRS rFPTransient(RFPTransientRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public RoomListRS roomList(RoomListRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public SearchRS search(SearchRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StatsRS stats(StatsRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StatsNotifRS statsNotif(StatsNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public StayInfoNotifRS stayInfoNotif(StayInfoNotifRQ request);

	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public SummaryNotifRS summaryNotif(SummaryNotifRQ request);

}

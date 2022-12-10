package org.jibx.schema.org.opentravel._2012A.test.hotel;


import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jibx.schema.org.opentravel._2012A.base.Customer;
import org.jibx.schema.org.opentravel._2012A.base.DateTimeSpan;
import org.jibx.schema.org.opentravel._2012A.base.DateTimeSpanGroup;
import org.jibx.schema.org.opentravel._2012A.base.PersonName;
import org.jibx.schema.org.opentravel._2012A.base.PromotionCodeGroup;
import org.jibx.schema.org.opentravel._2012A.hotel.AvailRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.AvailRequestSegments;
import org.jibx.schema.org.opentravel._2012A.base.hotelres.RatePlanCandidates;
import org.jibx.schema.org.opentravel._2012A.base.hotelres.RatePlanGroup;
import org.jibx.schema.org.opentravel._2012A.base.hotelres.RoomGroup;
import org.jibx.schema.org.opentravel._2012A.hotel.AvailRequestSegments.AvailRequestSegment.RoomStayCandidates;
import org.jibx.schema.org.opentravel._2012A.hotel.AvailRequestSegments.AvailRequestSegment.RoomStayCandidates.RoomStayCandidate;
import org.jibx.schema.org.opentravel._2012A.base.hotelres.RatePlanCandidates.RatePlanCandidate;
import org.jibx.schema.org.opentravel._2012A.base.profile.Profile;
import org.jibx.schema.org.opentravel._2012A.base.profile.Profiles;
import org.jibx.schema.org.opentravel._2012A.base.profile.Profiles.ProfileInfo;

import org.jibx.schema.org.opentravel._2012A.base.OTAPayloadStdAttributes;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.joda.time.DateTime;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Hello world!
 * Sample Jibx OTA OSGi program.
 */
public class HotelTest
	implements  BundleActivator
{
    public final static String STRING_ENCODING = "UTF8";
    public final static String URL_ENCODING = "UTF-8";

    /**
     * Standard constructor.
     */
    public HotelTest()
    {
    	super();
    }
    /**
     * Main
     * @param args
     */
    public static void main( String[] args )
    {
    	HotelTest app = new HotelTest();
        app.start(null);
    }
    /**
     * Run the test.
     */
    public void start(BundleContext context)
    {
    	Object message = createMessage();
    	String xml = marshalObject(message);
	    System.out.println(xml);
	    
	    message = unmarshalMessage(xml);
	    this.printMessage(message);
    }
    public void stop(BundleContext context)
    {
    	
    }
    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage()
    {
    	AvailRQ avail = new AvailRQ();
    	
    	AvailRequestSegments availRequestSegments = new AvailRequestSegments();
    	avail.setAvailRequestSegmentsAvailRequestSegments(availRequestSegments);
    	avail.setOTAPayloadStdAttributes(createStandardPayload());
    	
    	AvailRequestSegments.AvailRequestSegment availRequestSegment = new AvailRequestSegments.AvailRequestSegment();
    	availRequestSegments.addAvailRequestSegment(availRequestSegment);
    	
    	availRequestSegment.setAvailReq(AvailRequestSegments.AvailRequestSegment.AvailReq.ROOM);
    	DateTimeSpan stayDateRange = new DateTimeSpan();
    	DateTimeSpanGroup dateTimeSpanGroup = new DateTimeSpanGroup();
    	Calendar cal = Calendar.getInstance();
    	String start = this.dateToStringDateFormat(cal.getTime());
    	dateTimeSpanGroup.setStart(start);
    	int iDuration = 3;
    	dateTimeSpanGroup.setDuration(Integer.toString(iDuration));
        cal.add(Calendar.DATE, iDuration);
    	String end = this.dateToStringDateFormat(cal.getTime());
    	dateTimeSpanGroup.setEnd(end);
    	stayDateRange.setDateTimeSpanGroup(dateTimeSpanGroup);
    	availRequestSegment.setStayDateRange(stayDateRange);
    	
    	RatePlanCandidates ratePlanCandidates = new RatePlanCandidates();
    	availRequestSegment.setRatePlanCandidates(ratePlanCandidates);
    	RatePlanCandidate ratePlanCandidate = new RatePlanCandidate();
    	ratePlanCandidates.addRatePlanCandidate(ratePlanCandidate);
    	RatePlanGroup ratePlanGroup = new RatePlanGroup();
    	ratePlanCandidate.setRatePlanGroup(ratePlanGroup);
    	ratePlanGroup.setRatePlan("5");
    	//PromotionCodeGroup promotionCodeGroup = new PromotionCodeGroup();
    	//ratePlanGroup.setPromotionCodeGroup(promotionCodeGroup);
    	//promotionCodeGroup.setPromotionCode("123");
    	ratePlanGroup.setPromotionCode("123");
    	
    	RoomStayCandidates roomStayCandidates = new RoomStayCandidates();
    	availRequestSegment.setRoomStayCandidates(roomStayCandidates);
    	RoomStayCandidate roomStayCandidate = new RoomStayCandidate();
    	roomStayCandidates.addRoomStayCandidate(roomStayCandidate);
    	RoomGroup roomGroup = new RoomGroup();
    	roomStayCandidate.setRoomGroup(roomGroup);
    	roomGroup.setRoomCategory("ABC");
    	
    	Profiles profiles = new Profiles();
    	availRequestSegment.setProfiles(profiles);
    	ProfileInfo profileInfo = new ProfileInfo();
    	profiles.addProfileInfo(profileInfo);
    	Profile profile = new Profile();
    	profileInfo.setProfile(profile);
    	Customer customer = new Customer();
    	profile.setCustomer(customer);
    	List<PersonName> list = new Vector<PersonName>();
    	customer.setPersonNameList(list);
    	PersonName personName = new PersonName();
    	list.add(personName);
    	personName.setSurname("Doe");
    	personName.addGivenName("John");
    	
        return avail;
    }
    /**
     * Print the payload of this Hotel message.
     * @param message
     */
    public void printMessage(Object message)
    {
    	AvailRQ avail = (AvailRQ)message;
    	
    	AvailRequestSegments availRequestSegments = avail.getAvailRequestSegmentsAvailRequestSegments();
    	
    	if (availRequestSegments.sizeAvailRequestSegmentList() > 0)
    	{
    		AvailRequestSegments.AvailRequestSegment availRequestSegment = availRequestSegments.getAvailRequestSegment(0);
    		Profiles profiles = availRequestSegment.getProfiles();

    		if (profiles.sizeProfileInfoList() > 0)
    		{
	        	ProfileInfo profileInfo = profiles.getProfileInfo(0);
	        	Profile profile = profileInfo.getProfile();
	        	Customer customer = profile.getCustomer();
	        	List<PersonName> list = customer.getPersonNameList();
	        	if (list.size() > 0)
	        	{
	        		PersonName personName = list.get(0);
	        		String surName = personName.getSurname();
		        	String givenName = null;
		        	if (personName.sizeGivenNameList() > 0)
		        		givenName = personName.getGivenName(0);
		        	System.out.println("Name: " + givenName + " " + surName);
	        	}
    		}
    	}
    }

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final SimpleDateFormat gDateFormat = new SimpleDateFormat(DATE_FORMAT);
    /**
     * Convert this date to the standard string date format.
     * which is yyyy-MM-dd.
     * @param date
     * @return
     */
    public String dateToStringDateFormat(Date date)
    {
        if (date == null)
            return null;
        return gDateFormat.format(date);
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
     * Marshal this message to xml .
     * @param message
     * @param system
     * @return
     */
    public String marshalObject(Object message)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(AvailRQ.class);
			IMarshallingContext marshaller = jc.createMarshallingContext();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshalDocument(message, URL_ENCODING, null, out);
			String xml = out.toString(STRING_ENCODING);
			return xml;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
    }
    /**
     * Unmarshal this xml Message to an object.
     * @param xml
     * @param system
     * @return
     */
    public Object unmarshalMessage(String xml)
    {
        String bindingName = "hotel" + "_binding";
        
		try {
			IBindingFactory jc = BindingDirectory.getFactory(AvailRQ.class);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
	        Reader inStream = new StringReader(xml);
			Object message = unmarshaller.unmarshalDocument( inStream, bindingName);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
    	return null;
    }
}

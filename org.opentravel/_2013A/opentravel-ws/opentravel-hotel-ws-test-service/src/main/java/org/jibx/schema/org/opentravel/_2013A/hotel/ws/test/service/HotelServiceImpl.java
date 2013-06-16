package org.jibx.schema.org.opentravel._2013A.hotel.ws.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.transform.stream.StreamSource;

import org.jibx.schema.org.opentravel._2013A.base.PaymentCard;
import org.jibx.schema.org.opentravel._2013A.base.profilehotelres.Guarantee.GuaranteesAccepted.GuaranteeAccepted;
import org.jibx.schema.org.opentravel._2013A.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2013A.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2013A.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2013A.hotel.ResRS;
import org.jibx.schema.org.opentravel._2013A.hotel.ws.impl.DefaultHotelService;
import org.jibx.schema.ws.utilities.Utilities;

import com.sun.istack.logging.Logger;

public class HotelServiceImpl extends DefaultHotelService
{

	/**
	 * Constructor
	 */
	public HotelServiceImpl()
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
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public ResRS res(ResRQ request) {
		
		checkReqRequest(request);

		ResRS response = null;//createResRS(request);
    	Properties properties = new Properties();
    	properties.setProperty(Utilities.FILENAME, "/OTA_HotelResRS.xml");
    	properties.setProperty(Utilities.CLASSNAME, ResRQ.class.getName());
    	if (request.getOTAPayloadStdAttributes() != null)
    		if (request.getOTAPayloadStdAttributes().getEchoToken() != null)
        		if (request.getOTAPayloadStdAttributes().getEchoToken().startsWith("/"))
        	    	properties.setProperty(Utilities.FILENAME, request.getOTAPayloadStdAttributes().getEchoToken());
    	
    	response = (ResRS)this.createMessage(properties);
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
				
        return response;
	}

	/**
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public ResModifyRS resModify(ResModifyRQ request) {

		checkReqRequest(request);

		ResModifyRS response = null;//createResModifyRS(request);
    	Properties properties = new Properties();
    	properties.setProperty(Utilities.FILENAME, "/OTA_HotelResModifyRS.xml");
    	properties.setProperty(Utilities.CLASSNAME, ResModifyRQ.class.getName());
    	
    	response = (ResModifyRS)this.createMessage(properties);
		movePayloadData(request.getOTAPayloadStdAttributes(), response.getOTAPayloadStdAttributes());
				
        return response;
	}
	   private static final Logger logger = Logger.getLogger(HotelServiceImpl.class);

	/**
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public void checkReqRequest(ResRQ request) {
		
		logger.severe("This is a warning");

		try {
				GuaranteeAccepted guaranteeAccepted = request.getHotelReservations().getHotelReservation(0).getRoomStays().getRoomStay(0).getGuarantee(0).getGuaranteesAccepted().getGuaranteeAccepted(0);
				PaymentCard paymentCard = guaranteeAccepted.getChoice().getPaymentCard();
				checkPaymentCard(paymentCard);
				
				/* This is how you would do an XSLT transform:
				Document doc = TestUtilities.copyTreeToDOM(new DOMSource(UtilitiesTest.marshalObjectToDOM(request)), TestUtilities.XSL_CONVERT);
				System.out.println(TestUtilities.unmarshalSourceToXML(new DOMSource(doc)));
				*/
			} catch (Exception e) {
				// Null pointer exception - ignore for now
			}
					
	}
	
	/**
	 * Service the hotel request.
	 * @param request
	 * @return
	 */
	public void checkReqRequest(ResModifyRQ request) {
		
	}
	public void checkPaymentCard(PaymentCard paymentCard)
	{
		if (paymentCard.getSeriesCode() != null)
				System.out.println("Good - Card info good");
			else
				System.out.println("Error - Card info not good");
	}
	
	
	
    /**
     * Create a default message.
     * @return
     * ********* Note: This method was copied from UtilitiesTest - make it static *****
     */
    public Object createMessage(Properties properties)
    {
    	if (properties != null)
    		if (properties.getProperty(Utilities.FILENAME) != null)
    	{
    		String filename = properties.getProperty(Utilities.FILENAME);
    		InputStream stream = null;
    		URL url = this.getClass().getResource(filename);
    		try {
    			if (url != null)
    				stream = url.openStream();
			} catch (IOException e) {
				stream = null;
			}
    		if (stream == null)
				try {
					stream = new FileInputStream(filename);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
    		String className = properties.getProperty(Utilities.CLASSNAME);
    		if (className == null)
    		{
    			System.out.println("Error, you must specify unmarshalling file name for XML file " + filename);
    			return null;
    		}
    		try {
				return Utilities.unmarshalSource(new StreamSource(stream), Class.forName(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
}

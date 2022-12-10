package org.jibx.schema.org.opentravel.x2013B.hotel.ws.test.client;

import java.util.Properties;

import org.jibx.schema.org.opentravel.x2013B.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel.x2013B.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel.x2013B.hotel.ResRQ;
import org.jibx.schema.org.opentravel.x2013B.hotel.ResRS;
import org.jibx.schema.ws.utilities.Utilities;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;

import org.jibx.schema.org.opentravel.x2013B.base.ws.test.client.BaseOTAClientTest;

public class HotelClientTest extends BaseOTAClientTest
	implements  BundleActivator
{

	/**
	 * Constructor
	 */
	public HotelClientTest()
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

    public static final void main(String[] args)
    {
    	HotelClientTest test = new HotelClientTest();
    	test.start(null);
    }

    //private static final String endpoint = "http://localhost:8088/mockHotelSOAPBinding";
    private static final String endpoint = "http://localhost:8092/soap/hotel";
    private static final String restEndpoint = "http://localhost:8181/cxf/hotel/res";
    /**
     * Test it!
     */
    public void start(BundleContext context)
    {
    	Properties properties = new Properties();
    	properties.setProperty(ENDPOINT, endpoint);
    	properties.setProperty(FILENAME, "/OTA_HotelResRQ.xml");
    	properties.setProperty(CLASSNAME, ResRQ.class.getName());
    	
    	runTest(properties);	// By default, run the test once with no properties
    	
    	properties.setProperty(FILENAME, "/OTA_HotelResModifyRQ.xml");
    	properties.setProperty(CLASSNAME, ResModifyRQ.class.getName());
    	runTest(properties);	// By default, run the test once with no properties
    	
    	String response = this.sendRestMessage("/OTA_HotelResRQ.xml", restEndpoint);
		System.out.println("REST Response:\n" + response);
    }
    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage(Properties properties)
    {
    	Object request = super.createMessage(properties);
    	if (request instanceof ResRQ)
    		checkPayload(((ResRQ)request).getOTAPayloadStdAttributes());
    	else if (request instanceof ResModifyRQ)
    		checkPayload(((ResModifyRQ)request).getOTAPayloadStdAttributes());
		String xml = marshalObjectToXML(request);
	    System.out.println("Message to send:\n" + xml);
        return request;
    }
    /**
     * Check the contents of this message.
     * @param message
     */
    public void checkResponseMessage(Properties properties, Object message)
    {    	
    	String xml = marshalObjectToXML(message);
    	System.out.println(xml);
    }
}

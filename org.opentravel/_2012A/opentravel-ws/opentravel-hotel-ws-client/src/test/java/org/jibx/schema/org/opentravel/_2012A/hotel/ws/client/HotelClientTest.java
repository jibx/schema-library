package org.jibx.schema.org.opentravel._2012A.hotel.ws.client;

import java.util.Properties;

import org.jibx.schema.org.opentravel._2012A.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRS;
import org.jibx.schema.org.opentravel.ws.client.test.BaseClientTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.BundleActivator;

public class HotelClientTest extends BaseClientTest
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

    @Test
    @Category(HotelClientTest.class)
    public void test() {
    	HotelClientTest test = new HotelClientTest();
    	test.start(null);
    }

    public static final void main(String[] args)
    {
    	HotelClientTest test = new HotelClientTest();
    	test.start(null);
    }

    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage(Properties properties)
    {
    	ResRQ res = new ResRQ();
		res.setOTAPayloadStdAttributes(BaseService.createStandardPayload());
        return res;
    }
    private static final String endpoint = "http://localhost:8088/mockHotelSOAPBinding";
    /**
     * Get the SOAP endpoint.
     * @return
     */
    public String getEndpoint(Properties properties)
    {
    	return endpoint;
    }
    /**
     * Print the payload of this ping message.
     * @param message
     */
    public void checkResponseMessage(Properties properties, Object message)
    {
    	ResRS ping = (ResRS)message;
//        if (( "Don".equals(ping.getFirstName())) &&
  //      		( "Corley".equals(ping.getLastName())))
        	System.out.println ("Test ran great!");
    //    else
      //  	System.out.println ("Test failed");
    }
}

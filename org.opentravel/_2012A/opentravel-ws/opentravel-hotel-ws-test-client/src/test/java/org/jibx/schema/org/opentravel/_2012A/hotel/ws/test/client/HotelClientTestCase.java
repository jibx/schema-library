package org.jibx.schema.org.opentravel._2012A.hotel.ws.test.client;

import java.util.Properties;

import org.jibx.schema.org.opentravel._2012A.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRS;
import org.jibx.schema.ws.utilities.Utilities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;

import org.jibx.schema.org.opentravel._2012A.base.ws.test.client.BaseOTAClientTest;

public class HotelClientTestCase extends HotelClientTest
	implements  BundleActivator
{

	/**
	 * Constructor
	 */
	public HotelClientTestCase()
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
    	HotelClientTestCase test = new HotelClientTestCase();
    	test.start(null);
    }
    /**
     * Check the contents of this message.
     * @param message
     */
    public void checkResponseMessage(Properties properties, Object message)
    {    	
    	String xml = marshalObjectToXML(message);
    	System.out.println(xml);
    	
    	if (message instanceof ResRS)
	    	Assert.assertTrue("Return message is missing success element", (((ResRS)message).getSuccess() != null));
    	else if (message instanceof ResModifyRS)
	    	Assert.assertTrue("Return message is missing success element", (((ResModifyRS)message).getSuccess() != null));

    	Element element = marshalObjectToDOM(message);
    	String firstElement = element.getFirstChild().getLocalName();
    	Assert.assertTrue("Return message is missing success element", "Success".equalsIgnoreCase(firstElement));
    	Assert.assertTrue("Return message is missing success element",  Utilities.xPathTest(xml, "//ota:Success", "ota", "http://www.opentravel.org/OTA/2003/05"));
    }

}

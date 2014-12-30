package org.jibx.schema.org.opentravel._2014B.base.ws.test.client;

import java.util.Properties;

import org.jibx.schema.org.opentravel._2014B.base.OTAPayloadStdAttributes;
import org.joda.time.DateTime;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.jibx.schema.org.opentravel.schema.ws.base.rest.client.BaseRestClient;

public class BaseOTAClientTest extends BaseRestClient
	implements  BundleActivator
{

	/**
	 * Constructor
	 */
	public BaseOTAClientTest()
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
     * Test it!
     */
    public void start(BundleContext context)
    {
    }
    /**
     * Test it!
     */
    public void runTest(Properties properties)
    {
	    System.out.println("-----Start message client test-----");
	    super.runTest(properties);
	    System.out.println("-----End message client test-----");
    }
    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage(Properties properties)
    {
    	Object request = super.createMessage(properties);
        return request;
    }
    /**
     * Make sure the payload has the required values.
     * @param request
     */
    public static void checkPayload(OTAPayloadStdAttributes payloadStdAttributes)
    {
    	if (payloadStdAttributes.getTimeStamp() == null)
    		payloadStdAttributes.setTimeStamp(new DateTime());
    	if (payloadStdAttributes.getTimeStamp() == null)
    		payloadStdAttributes.setVersion(new Float(6.000));
    	if ((payloadStdAttributes.getTarget() == null) || (payloadStdAttributes.getTarget() == OTAPayloadStdAttributes.Target.PRODUCTION))
    		payloadStdAttributes.setTarget(OTAPayloadStdAttributes.Target.TEST);
    }
    /**
     * Check the contents of this message.
     * @param message
     */
    public void checkResponseMessage(Properties properties, Object message)
    {    	
    	// Override this to check the response
    }
}

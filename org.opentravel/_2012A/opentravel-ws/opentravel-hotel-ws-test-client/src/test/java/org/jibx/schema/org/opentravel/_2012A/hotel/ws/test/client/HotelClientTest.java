package org.jibx.schema.org.opentravel._2012A.hotel.ws.test.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jibx.schema.org.opentravel._2012A.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2012A.hotel.ResModifyRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.ResModifyRS;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRQ;
import org.jibx.schema.org.opentravel._2012A.hotel.ResRS;
import org.jibx.schema.ws.utilities.client.BaseClientTest;
import org.jibx.schema.ws.utilities.Utilities;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;

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
    	
    	String response = this.sendRestMessage("/OTA_HotelResRQ.xml");
		System.out.println("REST Response:\n" + response);
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
    	if (request instanceof ResRQ)
    		checkPayload(((ResRQ)request).getOTAPayloadStdAttributes());
    	else if (request instanceof ResModifyRQ)
    		checkPayload(((ResModifyRQ)request).getOTAPayloadStdAttributes());
		String xml = marshalObjectToXML(request);
	    System.out.println("Message to send:\n" + xml);
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
     * Print the payload of this ping message.
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
    /**
     * Send a xml REST message.
     * @param filename
     * @return
     */
    public String sendRestMessage(String filename)
    {
    	try {
	    	HttpClient httpclient = new DefaultHttpClient();
	    	HttpPost post = new HttpPost(restEndpoint);

	    	MultipartEntity entity = new MultipartEntity();
    		InputStream inStream = Utilities.getStream(getClass(), filename);
	    	ContentBody contentBody = new InputStreamBody(inStream, "text/xml", filename);
	    	entity.addPart("file", contentBody);
	    	post.setEntity(entity);

			HttpResponse response = httpclient.execute(post);
			HttpEntity httpEntity = response.getEntity();
			InputStream inputStream = httpEntity.getContent();
			Reader reader = new InputStreamReader(inputStream);
			Writer writer = new StringWriter();
			Utilities.transferStream(reader, writer);
			return writer.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
}

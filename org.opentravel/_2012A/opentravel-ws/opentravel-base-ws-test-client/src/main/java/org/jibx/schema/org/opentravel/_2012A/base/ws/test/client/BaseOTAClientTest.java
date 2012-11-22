package org.jibx.schema.org.opentravel._2012A.base.ws.test.client;

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
import org.jibx.schema.ws.utilities.Utilities;
import org.jibx.schema.ws.utilities.client.BaseClientTest;
import org.joda.time.DateTime;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BaseOTAClientTest extends BaseClientTest
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
    /**
     * Send a xml REST message.
     * @param filename
     * @return
     */
    public String sendRestMessage(String filename, String restEndpoint)
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

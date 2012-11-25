package org.jibx.schema.org.opentravel.schema.ws.base.rest.client;

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
import org.jibx.schema.ws.utilities.Utilities;
import org.jibx.schema.ws.utilities.client.BaseClient;
import org.joda.time.DateTime;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BaseRestClient extends BaseClient
	implements  BundleActivator
{

	/**
	 * Constructor
	 */
	public BaseRestClient()
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

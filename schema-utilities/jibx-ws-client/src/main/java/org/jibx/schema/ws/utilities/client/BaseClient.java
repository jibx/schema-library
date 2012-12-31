package org.jibx.schema.ws.utilities.client;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.jibx.schema.ws.utilities.Utilities;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Element;

@SuppressWarnings("restriction")
public abstract class BaseClient extends Utilities
	implements  BundleActivator
{

	/**
	 * Constructor
	 */
	public BaseClient()
	{
		super();
	}

	/**
	 * Constructor
	 */
	public void init()
	{
//		super.init();
	}

    /**
     * Test it!
     */
    public void start(BundleContext context)
    {
    	runTest(null);	// By default, run the test once with no properties
    }
    /**
     * Test it!
     */
    public void runTest(Properties properties)
    {
    	try {
        	Object jibxMessage = createMessage(properties);
    	    
    	    SOAPMessage soapMessage = createSOAPMessage(jibxMessage);
	        
	        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
	        SOAPMessage soapResponse = connection.call(soapMessage, getEndpoint(properties));
	        connection.close();
	 
	        Object res = getResponseFromSOAPMessage(soapResponse, getResponseClass(jibxMessage));
	        checkResponseMessage(properties, res);
    	} catch (SOAPException e) {
    		e.printStackTrace();
    	} 
    }
    /**
     * End of test.
     */
    public void stop(BundleContext context)
    {
    	
    }

    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage(Properties properties)
    {
    	if (properties != null)
    		if (properties.getProperty(FILENAME) != null)
    	{
    		String filename = properties.getProperty(FILENAME);
    		InputStream stream = getStream(this.getClass(), filename);
    		String className = properties.getProperty(CLASSNAME);
    		if (className == null)
    		{
    			System.out.println("Error, you must specify unmarshalling file name for XML file " + filename);
    			return null;
    		}
    		try {
				return unmarshalSource(new StreamSource(stream), Class.forName(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    /**
     * Get the SOAP endpoint.
     * @return
     */
    public String getEndpoint(Properties properties)
    {	// Override this
    	if (properties != null)
    		return properties.getProperty(ENDPOINT);
    	return null;
    }
    /**
     * Check the payload of this response message.
     * @param message
     */
    public void checkResponseMessage(Properties properties, Object message)
    {
    }
    /**
     * Create a SOAP message with this jibx message in it.
     * @param jibxMessage
     * @return
     * @throws SOAPException
     */
	public static SOAPMessage createSOAPMessage(Object jibxMessage) throws SOAPException
	{
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPHeader header = soapMessage.getSOAPHeader();
        header.detachNode();

        SOAPBody body = soapMessage.getSOAPBody();
        
        DOMResult result = new DOMResult(body);
        Element resElement = marshalObjectToDOM(jibxMessage);
        DOMSource source = new DOMSource(resElement);
        copyTreeToResult(source, result);
        return soapMessage;
	}
    /**
     * Extract the jibx response message from this SOAP response.
     * @param response
     * @param clazz
     * @return
     * @throws SOAPException
     */
	public static Object getResponseFromSOAPMessage(SOAPMessage response, Class<?> clazz) throws SOAPException
    {
        SOAPBody body = response.getSOAPBody();
        
        Element node = null;
        Iterator<?> iterator = body.getChildElements();
        while (iterator.hasNext())
        {
            Object nextNode = iterator.next();
            if (!(nextNode instanceof Element))
                continue;
            node = (Element)nextNode;
        
            if (node instanceof SOAPElement)    // The one and only
                break;
        }
        if(body.getFault()!=null){
            System.out.println("Fault: "+body.getFault().getFaultString());
        }
        if (node != null)
        {
        	Source source = new DOMSource(node);
        	Object res = unmarshalSource(source, clazz);
        	return res;
        }
        else
        	return null;	// Error
    }
	/**
	* Get the SOAP element with this name.
	* @param elementName The element to return (if null, return the first element).
	*/
	public SOAPElement getSOAPElement(SOAPElement element, String elementName)
	{
		Iterator<?> iterator = element.getChildElements();
		while (iterator.hasNext())
		{
		    javax.xml.soap.Node elMessageType = (javax.xml.soap.Node)iterator.next();
		    if (elMessageType instanceof SOAPElement)
		    {
		        if (elementName == null)
		            return (SOAPElement)elMessageType;    // The message type
		        if (elementName.equalsIgnoreCase(((SOAPElement)elMessageType).getElementName().getLocalName()))
		            return (SOAPElement)elMessageType;    // The message type
		    }
		}
		return null;    // not found
	}
	/**
	 * Given the request class, get the response class.
	 */
    public Class<?> getResponseClass(Object jibxMessage)
    {
    	String requestClassName = jibxMessage.getClass().getName();
    	if (!requestClassName.endsWith("RQ"))
    		return null;
    				
    	String responseClassName = requestClassName.substring(0, requestClassName.length() - 2) + "RS";
    	try {
			return Class.forName(responseClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
    }
}

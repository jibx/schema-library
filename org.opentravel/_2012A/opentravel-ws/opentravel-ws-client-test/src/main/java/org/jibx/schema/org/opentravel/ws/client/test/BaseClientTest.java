package org.jibx.schema.org.opentravel.ws.client.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
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
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.jibx.extras.JDOMWriter;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SuppressWarnings("restriction")
public abstract class BaseClientTest extends Object
	implements  BundleActivator
{
    public static final String FILENAME = "filename";
    public static final String CLASSNAME = "classname";
    public static final String ENDPOINT = "endpoint";

	/**
	 * Constructor
	 */
	public BaseClientTest()
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
    public final static String STRING_ENCODING = "UTF8";
    public final static String URL_ENCODING = "UTF-8";
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
     * Marshal this message to xml .
     * @param message
     * @return XML String
     */
    public static String marshalObjectToXML(Object message)
    {
    	if (message == null)
    		return null;
		try {
			IBindingFactory jc = BindingDirectory.getFactory(message.getClass());
			IMarshallingContext marshaller = jc.createMarshallingContext();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.setIndent(4);
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
     * Marshal this message to DOM.
     * @param message
     * @return DOM tree
     */
    public static Element marshalObjectToDOM(Object message)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(message.getClass());
			IMarshallingContext marshaller = jc.createMarshallingContext();

        	String[] namespaces = jc.getNamespaces();
            JDOMWriter jdomWriter = new JDOMWriter(namespaces);
            marshaller.setXmlWriter(jdomWriter);
            marshaller.marshalDocument(message);
            marshaller.endDocument();
            
            org.jdom.Document jdoc = jdomWriter.getDocument();
            DOMOutputter dout = new DOMOutputter();
            Document doc = dout.output(jdoc);
            Element node = doc.getDocumentElement();
			
			return node;
		} catch (JiBXException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
    }
    /**
     * Unmarshal this xml Message to a JIBX object.
     * @param xml
     * @param system
     * @return
     */
    public static Object unmarshalMessage(String xml, Class<?> clazz)
    {        
		try {
			IBindingFactory jc = BindingDirectory.getFactory(clazz);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
	        Reader inStream = new StringReader(xml);
			Object message = unmarshaller.unmarshalDocument(inStream);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * Unmarshal this DOM source to a JIBX object.
     * @param xml
     * @param system
     * @return
     */
    public static Object unmarshalSource(Source source, Class<?> clazz)
    {
        Writer writer = new StringWriter();
        Result result = new StreamResult(writer);

        copyTreeToResult(source, result);

        try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String strXMLBody = writer.toString();
        
        Object msg = unmarshalMessage(strXMLBody, clazz);
    	return msg;
    }
    /**
     * Copy DOM tree to a DOM result tree.
     * @param tree
     * @param node
     * @return The parent of the new child node.
     */
    public static boolean copyTreeToResult(Source source, Result result)
    {
        try {
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            transformer.transform(source, result);
            return true;    // Success
        } catch (TransformerConfigurationException tce) {
            // Error generated by the parser
            tce.printStackTrace();
        } catch (TransformerException te) {
            // Error generated by the parser
            te.printStackTrace();
        }
        return false;   // Failure
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

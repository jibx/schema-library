package org.jibx.schema.ws.utilities;

import java.io.ByteArrayInputStream;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.jibx.extras.JDOMWriter;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utilities {
    
	public final static String STRING_ENCODING = "UTF8";
    public final static String URL_ENCODING = "UTF-8";

    /**
     * Return true if this XPath expression evaluates to a non-null set.
     * @param xml
     * @param path
     * @param prefix
     * @param namespace
     * @return
     */
    public static boolean xPathTest(String xml, String path, String prefix, String namespace)
    {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
    		InputStream stream = new ByteArrayInputStream(xml.getBytes(URL_ENCODING));
            Document doc = builder.parse(stream);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
    		SimpleNamespaceContext context = new SimpleNamespaceContext(prefix, namespace);
    		xpath.setNamespaceContext(context);
            XPathExpression expr = xpath.compile(path);
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            return (nodes.getLength()> 0);
        } catch (ParserConfigurationException e) {
        	e.printStackTrace();
        } catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
        return false;
    }
    /**
     * Copy DOM tree to a DOM result tree.
     * @param tree
     * @param node
     * @return The parent of the new child node.
     */
    public static Document copyTreeToDOM(Source source, String xslt)
    {
    	Source xsltSource;
		try {
			xsltSource = new StreamSource(new ByteArrayInputStream(Utilities.XSL_CONVERT.getBytes(URL_ENCODING)));
			return copyTreeToDOM(source, xsltSource);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
    }
    /**
     * Copy DOM tree to a DOM result tree.
     * @param tree
     * @param node
     * @return The parent of the new child node.
     */
    public static Document copyTreeToDOM(Source source, Source xslt)
    {
        try {
        	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        	builderFactory.setNamespaceAware(true);
        	DocumentBuilder builder = builderFactory.newDocumentBuilder();
        	Document document = builder.newDocument();
        	Result result = new DOMResult(document);
        	copyTreeToResult(source, result, xslt);
        	return document;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
    }
    /**
     * Copy DOM tree to a DOM result tree.
     * @param tree
     * @param node
     * @param xslt XSL Transformation source
     * @return The parent of the new child node.
     */
    public static boolean copyTreeToResult(Source source, Result result, Source xslt)
    {
        try {
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(xslt);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
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
     * Unmarshal this DOM source to a JIBX object.
     * @param xml
     * @param system
     * @return
     */
    public static String unmarshalSourceToXML(Source source)
    {
        Writer writer = new StringWriter();
        Result result = new StreamResult(writer);

        Utilities.copyTreeToResult(source, result);

        try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String strXMLBody = writer.toString();

        return strXMLBody;
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
     * A default XSL document that does no conversion.
     */
    protected static String XSL_CONVERT =
		"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
		"<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:ota=\"http://www.opentravel.org/OTA/2003/05\" version=\"1.0\"  >" +
		//" <xsl:template match=\"\">" +
		//"  <xsl:copy-of select=\".\" />" +
		//" </xsl:template>" +
		"<!-- This template copies the name and attributes and applies templates to child nodes -->" +
		"<xsl:template match=\"*\">" +
		"  <xsl:copy>" +
		"    <xsl:for-each select=\"@*\">" +
		"      <xsl:attribute name=\"{name()}\">" +
		"        <xsl:value-of select=\".\"/>" +
		"      </xsl:attribute>" +
		"    </xsl:for-each> " +
		"  	<xsl:apply-templates />" +
		"  </xsl:copy>" +
		"</xsl:template>" +
		
		//"<xsl:template match=\"ota:Surname\">Smith</xsl:template>" +
		
		//"<xsl:template match=\"ota:roomType\">" +
		//" <xsl:element name=\"ota:roomzType\">" +
		//"  <xsl:apply-templates />" +
		//" </xsl:element>" +
		//"</xsl:template>" +
		
		"</xsl:stylesheet>";
    /**
     * Namespace lookup implementation.
     * @author Don
     *
     */
    public static class SimpleNamespaceContext extends HashMap<String,String>
    	implements NamespaceContext
    	{
		private static final long serialVersionUID = 1L;
		public SimpleNamespaceContext() {
    		super();
    	}
    	public SimpleNamespaceContext(String prefix, String namespace) {
    		this();
    		this.put(prefix, namespace);
    	}
		public String getNamespaceURI(String prefix) {
			return get(prefix);
		}
		public String getPrefix(String namespaceURI) {
			for (Map.Entry<String,String> entry : this.entrySet())
			{
				if (namespaceURI.equalsIgnoreCase(entry.getValue()))
						return entry.getKey();
			}
			return null;
		}
		public Iterator<String> getPrefixes(String namespaceURI) {
            System.out.println("Failed to parse!" + namespaceURI);
			Set<String> set = new HashSet<String>();
			for (Map.Entry<String,String> entry : this.entrySet())
			{
				if (namespaceURI.equalsIgnoreCase(entry.getValue()))
						set.add(entry.getKey());
			}			
			return set.iterator();
		}
    	
    	}
    /**
     * Transfer the data stream from in stream to out stream.
     * Note: This does not close the out stream.
     * @param in Stream in
     * @param out Stream out
     */
    public static void transferStream(Reader in, Writer out)
    {
        try {
            char[] cbuf = new char[1000];
            int iLen = 0;
            while ((iLen = in.read(cbuf, 0, cbuf.length)) > 0)
            {   // Write the entire file to the output buffer
                out.write(cbuf, 0, iLen);
            }
            in.close();
        } catch (MalformedURLException ex)  {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Create a stream from this file.
     * @return
     */
    public static InputStream getStream(Class<?> cl, String filename)
    {
		InputStream stream = null;
		URL url = cl.getResource(filename);
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
    	return stream;
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
}

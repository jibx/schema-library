package org.jibx.android.test.app;

import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.jibx.android.schema.person.Person;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class appActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mPickDate = (Button) findViewById(R.id.button1);
		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				nothing();
			}
		});
	}

	Button mPickDate = null;

	public void nothing() {
		String text = "nothing";

		text = this.addTestTableRecords();

		mPickDate.setText(text);
	}

	/**
	 * Add the test table records.
	 */
	public String addTestTableRecords() {
    	Object message = createMessage();
    	String xml = marshalObject(message);
	    System.out.println(xml);
	    
	    message = unmarshalMessage(xml);
	    System.out.println(getDataFromMessage(message));
		return getDataFromMessage(message);
	}
    /**
     * Create a default ping message.
     * @return
     */
    public Object createMessage()
    {
    	Person person = new Person();
    	person.setFirstName("Don");
    	person.setLastName("Corley");
        return person;
    }
    /**
     * Print the payload of this ping message.
     * @param message
     */
    public String getDataFromMessage(Object message)
    {
    	Person person = (Person)message;
    	String first = person.getFirstName();
    	String last = person.getLastName();
    	return first + " " + last;
    }
    /**
     * Marshal this message to xml .
     * @param message
     * @param system
     * @return
     */
    public final static String STRING_ENCODING = "UTF8";
    public final static String URL_ENCODING = "UTF-8";
    String packageName = "org.jibx.android.schema.person";
    String bindingName = "binding";
    public String marshalObject(Object message)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, packageName);
			IMarshallingContext marshaller = jc.createMarshallingContext();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
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
     * Unmarshal this xml Message to an object.
     * @param xml
     * @param system
     * @return
     */
    public Object unmarshalMessage(String xml)
    {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, packageName);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
	        Reader inStream = new StringReader(xml);
			Object message = unmarshaller.unmarshalDocument( inStream, bindingName);
			return message;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
    	return null;
    }
}

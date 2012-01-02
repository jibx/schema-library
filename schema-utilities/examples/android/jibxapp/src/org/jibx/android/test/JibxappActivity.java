package org.jibx.android.test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.org.jibx.sampleschema.person.Person;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JibxappActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mPickDate = (Button) findViewById(R.id.button1);
		mFirstName = (EditText) findViewById(R.id.editText1);
		mLastName = (EditText) findViewById(R.id.editText2);
		mTextBox = (EditText) findViewById(R.id.editText3);
		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				doJibxTest();
			}
		});
	}

	Button mPickDate = null;
	EditText mFirstName = null;
	EditText mLastName = null;
	EditText mTextBox = null;

	/**
	 * Add the test table records.
	 */
	public void doJibxTest() {
		Person person = new Person();
		person.setFirstName(mFirstName.getText().toString());
		person.setLastName(mLastName.getText().toString());

		String xml = marshalMessage(person);
		mTextBox.setText(xml);

		Person personOut = (Person) unmarshalMessage(xml);

		mPickDate.setText(personOut.getFirstName() + ' '
				+ personOut.getLastName());
	}

	/**
	 * Marshal this message to xml .
	 * 
	 * @param message
	 * @param system
	 * @return
	 */
	public final static String STRING_ENCODING = "UTF8";
	public final static String URL_ENCODING = "UTF-8";
	String bindingName = "binding";

	public String marshalMessage(Object message) {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName,
					Person.class);
			IMarshallingContext marshaller = jc.createMarshallingContext();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshalDocument(message, URL_ENCODING, null, out);
			return out.toString(STRING_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Unmarshal this xml Message to an object.
	 * 
	 * @param xml
	 * @param system
	 * @return
	 */
	public Object unmarshalMessage(String xml) {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName,
					Person.class);
			IUnmarshallingContext unmarshaller = jc
					.createUnmarshallingContext();
			return unmarshaller.unmarshalDocument(new StringReader(xml),
					bindingName);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
	}
}

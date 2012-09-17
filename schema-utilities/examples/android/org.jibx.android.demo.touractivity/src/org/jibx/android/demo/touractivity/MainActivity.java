package org.jibx.android.demo.touractivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.org.opentravel._2012A.base.Errors;
import org.jibx.schema.org.opentravel._2012A.base.OTAPayloadStdAttributes;
import org.jibx.schema.org.opentravel._2012A.base._Error;
import org.jibx.schema.org.opentravel._2012A.base.touractivity.TourActivityDescription;
import org.jibx.schema.org.opentravel._2012A.base.touractivity.TourActivityID;
import org.jibx.schema.org.opentravel._2012A.base.ws.BaseService;
import org.jibx.schema.org.opentravel._2012A.touractivity.SearchRS;
import org.jibx.schema.org.opentravel._2012A.touractivity.SearchRQ;
import org.jibx.schema.org.opentravel._2012A.touractivity.SearchRQ.DateTimePref;
import org.jibx.schema.org.opentravel._2012A.touractivity.SearchRS.TourActivityInfo;
import org.jibx.schema.org.opentravel._2012A.touractivity.SearchRS.TourActivityInfo.Pricing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Tour Activity display.
 * Display the tour activities using the entered date.
 * Clicking on a tour will display the detail.
 * @author Don Corley <don@tourgeek.com>
 */
public class MainActivity extends Activity {
	
	ArrayAdapter<String> adapter = null;	// List items 
	ProgressBar progressBar = null;
	TextView statusView = null;
	
	String host;
	String path;
	
	public static final String HOST = "host";
	public static final String PATH = "path";
	public static final String DEFAULT_HOST = "192.168.1.100:8181";
	public static final String DEFAULT_PATH = "/cxf/touractivity/search";
	
	/**
	 * Setup.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        ListView lv = (ListView)this.findViewById(R.id.listView);
        DatePicker datePicker = (DatePicker)this.findViewById(R.id.datePicker);
        progressBar = (ProgressBar)this.findViewById(R.id.progressBar);
        statusView = (TextView)this.findViewById(R.id.status);
        
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_text_view, R.id.listViewTextView);
        // Binding resources Array to ListAdapter
        lv.setAdapter(adapter);
 
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 
              // selected item
        	  TourInfo tourInfo = mTourInfoList.get(position);
 
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("tourID", tourInfo.tourID);
              i.putExtra("description", tourInfo.description);
              i.putExtra("minPrice", String.format("%.2f%n", tourInfo.minPrice));
              i.putExtra("maxPrice", String.format("%.2f%n", tourInfo.maxPrice));
              startActivity(i);
          }
        });
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());	// Initial date = today
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // Requery the list when the date changes.
        DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener()
        {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				requeryProduct(year, monthOfYear, dayOfMonth);	// Requery using new date
			}
        	
        };
        datePicker.init(year, monthOfYear, dayOfMonth, onDateChangedListener);

        // Get the saved settings
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        host = preferences.getString(HOST, DEFAULT_HOST);
        path = preferences.getString(PATH, DEFAULT_PATH);

    	// Do initial display
		requeryProduct(year, monthOfYear, dayOfMonth);
    }

    /**
     * Settings menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    /**
     * Display settings menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                i.putExtra(HOST, host);
                i.putExtra(PATH, path);
                startActivityForResult(i, R.id.menu_settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Return from settings menu.
     */
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == R.id.menu_settings) {
            if (resultCode == RESULT_OK) {
                // Save the new settings.
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                
                Editor editor = preferences.edit();
                editor.putString(HOST, host = data.getStringExtra(HOST));
                editor.putString(PATH, path = data.getStringExtra(PATH));
                editor.commit();
                
            	// Requery using new date
                DatePicker datePicker = (DatePicker)this.findViewById(R.id.datePicker);
				requeryProduct(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            }
        }
    }

    /**
     * Set up to requery the product using this criteria.
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    public void requeryProduct(int year, int monthOfYear, int dayOfMonth)
    {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, monthOfYear);
    	calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	Date date = calendar.getTime();
        String YMDdate = convertDateToYMD(date);
        new DownloadFilesTask().execute(YMDdate);
    }
    
    /**
     * Make sure SOA query is not done in the UI thread.
     * @author don
     */
    private class DownloadFilesTask extends AsyncTask<String, Boolean, List<TourInfo>> {
        protected List<TourInfo> doInBackground(String... criteria) {
        	return doProductQuery(criteria[0]);
        }

        protected void onPreExecute() {
        	adapter.clear();
        	progressBar.setVisibility(ProgressBar.VISIBLE);
        	progressBar.setIndeterminate(true);
        	statusView.setVisibility(TextView.VISIBLE);
        	statusView.setText("Please wait... retrieving data");
        }

        protected void onPostExecute(List<TourInfo> tourInfoList) {
            // storing string resources into Array
        	mTourInfoList = tourInfoList;
        	if (tourInfoList != null)
        	{
	            for (TourInfo tourInfo : tourInfoList)
	            {
	            	adapter.add(tourInfo.description);
	            }
        	}
        	progressBar.setVisibility(ProgressBar.GONE);
        	progressBar.setIndeterminate(false);
        	statusView.setVisibility(TextView.GONE);
        }
    }
    
    /**
     * Do the actual product query.
     * @param YMDdate
     * @return The list of tours (null if empty).
     */
    protected List<TourInfo> doProductQuery(String YMDdate) {
    	List<TourInfo> tourProducts = null;
        
    	// Populate the search request
        SearchRQ searchRQ = new SearchRQ();
        OTAPayloadStdAttributes payload = BaseService.createStandardPayload();
        searchRQ.setOTAPayloadStdAttributes(payload);
        DateTimePref dateTimePref = new DateTimePref();
        searchRQ.setDateTimePref(dateTimePref);
        dateTimePref.setStart(YMDdate);
        dateTimePref.setEnd(YMDdate);
        
        // Send it to the server and get the result
        SearchRS searchRS = search(searchRQ);
        if (searchRS == null)
        	return null;
        
        // Extract the information from the results
        tourProducts = new Vector<TourInfo>();
        List<TourActivityInfo> tourActivities = searchRS.getTourActivityInfoList();
        if ((tourActivities == null) || (searchRS.getErrors() != null))
        	return getErrorMessage(searchRS);
        for (TourActivityInfo tourActivityInfo : tourActivities)
        {
        	TourInfo tourInfo = new TourInfo();
        	TourActivityID tourActivityID = tourActivityInfo.getBasicInfo();
        	tourInfo.tourID = tourActivityID.getTourActivityID();
        	TourActivityDescription tourActivityDescription =  tourActivityInfo.getDescription();
        	if (tourActivityDescription != null)
        		tourInfo.description = tourActivityDescription.getShortDescription();
        	Pricing pricing = tourActivityInfo.getPricing();
        	if (pricing != null)
        	{
        		tourInfo.minPrice = pricing.getMinPrice();
        		tourInfo.maxPrice = pricing.getMaxPrice();
        	}
        	tourProducts.add(tourInfo);
        }

        // Return the tours to display
        return tourProducts;
    }
    /**
     * Extract the error message and set up a fake list this this message.
     * @param searchRS
     * @return
     */
    protected List<TourInfo> getErrorMessage(SearchRS searchRS)
    {
    	List<TourInfo> tourProducts = new Vector<TourInfo>();
    	TourInfo tourInfo = new TourInfo();
    	if (searchRS.getErrors() != null)
    		tourInfo.description = searchRS.getErrors().getError(0).getString();
    	tourProducts.add(tourInfo);
        return tourProducts;
    }
	/**
	 * Given the search request, do the search.
	 * @param searchRQ The search request
	 * @return The search response
	 */
	public SearchRS search(SearchRQ searchRQ)
    {
        String send = marshalMessage(searchRQ);
        
        String reply = search(send);
        SearchRS searchRS = null;
        if ((reply != null) && (reply.startsWith("<")))
        	searchRS = (SearchRS)this.unmarshalMessage(reply, SearchRS.class);
        else
        {	// Error - Create fake error response message
        	searchRS = new SearchRS();
        	Errors errors = new Errors();
        	searchRS.setErrors(errors);
        	_Error item = new _Error();
        	errors.addError(item);
        	if ((reply == null) || (reply.length() == 0))
        		reply = "No data returned from query";
        	item.setString("Error: " + reply);
        }
        return searchRS;
    }
	/**
	 * Given the xml search request, do the search.
	 * @param searchRQ The xml search request
	 * @return The xml search response
	 */
	public String search(String searchRQ)
    {
        String responseXML;
        try {
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://" + host + path);

	        StringEntity se = new StringEntity(searchRQ, HTTP.UTF_8);

	        se.setContentType("text/xml");  
	        httppost.setHeader("Content-Type","application/xml;charset=UTF-8");
	        httppost.setEntity(se);  

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
            HttpEntity responseEntity = response.getEntity();
            InputStream in = responseEntity.getContent();
            // A simple way to move a stream to a string
                responseXML = new java.util.Scanner(in).useDelimiter("\\A").next();
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        	responseXML = e.getMessage();
        } catch (IOException e) {
        	e.printStackTrace();
        	responseXML = e.getMessage();
        } catch (java.util.NoSuchElementException e) {
            responseXML = e.getMessage();
		}
        return responseXML;
    } 
    
    protected List<TourInfo> mTourInfoList = null;	// Note: Added for simplicity - Fix concurrency issue
	/**
	 * Simple tour info data structure.
	 */
	public static class TourInfo
    {
    	public TourInfo()
    	{
        	tourID = "";
        	description = "";
        	minPrice = 0f;
        	maxPrice = 0f;    		
    	}
    	String tourID;
    	String description;
    	Float minPrice;
    	Float maxPrice;
    }

	public final static String STRING_ENCODING = "UTF8";
	public final static String URL_ENCODING = "UTF-8";
	String bindingName = "binding";
	/**
	 * Utility to convert data to an XML string.
	 * @param message The data to convert.
	 * @return XML or null if it could not be converted.
	 */
	public String marshalMessage(Object message) {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, message.getClass());
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
	 * Utility to unmarshal this XML string to an object.
	 * @param xml string
	 * @param clazz The target object
	 * @return The data object.
	 */
	public Object unmarshalMessage(String xml, Class<?> clazz) {
		try {
			IBindingFactory jc = BindingDirectory.getFactory(bindingName, clazz);
			IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
			return unmarshaller.unmarshalDocument(new StringReader(xml), bindingName);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static final DateFormat yyyymmddDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Utility to convert a Date object to yyyy-mm-dd.
	 * @param date
	 * @return ymdDate
	 */
	public static String convertDateToYMD(Date date)
	{
			return yyyymmddDateFormat.format(date);
	}
}

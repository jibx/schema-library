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
import org.jibx.schema.org.opentravel._2012A.base.OTAPayloadStdAttributes;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	ArrayAdapter<String> adapter = null;
	ProgressBar progressBar = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)this.findViewById(R.id.listView);
        DatePicker datePicker = (DatePicker)this.findViewById(R.id.datePicker);
        progressBar = (ProgressBar)this.findViewById(R.id.progressBar);
        
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_text_view, R.id.listViewTextView);
        // Binding resources Array to ListAdapter
        lv.setAdapter(adapter);
 
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 
              // selected item
        	  TourInfo tourInfo = mTourInfoList.get(position);
              String product = ((TextView) view).getText().toString();
 
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
        calendar.setTime(new Date());
        //calendar.add(Calendar.MONTH, 1);
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener()
        {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				requeryProduct(year, monthOfYear, dayOfMonth);	// Requery using new date
			}
        	
        };
        datePicker.init(year, monthOfYear, dayOfMonth, onDateChangedListener);

		requeryProduct(year, monthOfYear, dayOfMonth);	// Do initial display
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

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
    
    private class DownloadFilesTask extends AsyncTask<String, Boolean, List<TourInfo>> {
        protected List<TourInfo> doInBackground(String... criteria) {
        	return doProductQuery(criteria[0]);
        }

        protected void onPreExecute() {
        	progressBar.setVisibility(ProgressBar.VISIBLE);
        	progressBar.setIndeterminate(true);
        	adapter.clear();
        }

        protected void onProgressUpdate(Boolean... progress) {
        	progressBar.setVisibility(ProgressBar.VISIBLE);
        	progressBar.setIndeterminate(progress[0]);
        }

        protected void onPostExecute(List<TourInfo> tourInfoList) {
            // storing string resources into Array
        	mTourInfoList = tourInfoList;
            for (TourInfo tourInfo : tourInfoList)
            {
            	adapter.add(tourInfo.description);
            }
        	progressBar.setVisibility(ProgressBar.GONE);
        	progressBar.setIndeterminate(false);
        }
    }
    protected List<TourInfo> mTourInfoList = null;
    
    protected List<TourInfo> doProductQuery(String YMDdate) {
    	List<TourInfo> tour_products = null;
        
        SearchRQ searchRQ = new SearchRQ();
        OTAPayloadStdAttributes payload = BaseService.createStandardPayload();
        searchRQ.setOTAPayloadStdAttributes(payload);
        DateTimePref dateTimePref = new DateTimePref();
        searchRQ.setDateTimePref(dateTimePref);
        dateTimePref.setStart(YMDdate);
        dateTimePref.setEnd(YMDdate);
        
        String send = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<OTA_TourActivitySearchRQ" +
"    Target=\"Production\"" +
"    TimeStamp=\"2012-09-04T07:09:18.513Z\"" +
"    Version=\"1.0\"" +
"    xmlns=\"http://www.opentravel.org/OTA/2003/05\" >" +
"        " +
"    <SearchCriteria>" +
"    " +
"        <DateTimePref" +
"            End=\"2012-09-05\"" +
"            Start=\"2012-09-05\" />" +
"    </SearchCriteria>" +
"    " +
"</OTA_TourActivitySearchRQ>";
        send = this.marshalMessage(searchRQ);
        
        String reply = search(send);
        SearchRS searchRS = null;
        if (reply != null)
        	searchRS = (SearchRS)this.unmarshalMessage(reply, SearchRS.class);
        
        if (searchRS == null)
        	return null;
        
        tour_products = new Vector<TourInfo>();
        List<TourActivityInfo> tourActivities = searchRS.getTourActivityInfoList();
        for (TourActivityInfo tourActivityInfo : tourActivities)
        {
        	TourInfo tourInfo = new TourInfo();
        	TourActivityID tourActivityID = tourActivityInfo.getBasicInfo();
        	tourInfo.tourID = tourActivityID.getTourActivityID();
        	TourActivityDescription tourActivityDescription =  tourActivityInfo.getDescription();
        	tourInfo.description = tourActivityDescription.getShortDescription();
        	Pricing pricing = tourActivityInfo.getPricing();
        	tourInfo.minPrice = pricing.getMinPrice();
        	tourInfo.maxPrice = pricing.getMaxPrice();
        	
        	tour_products.add(tourInfo);
        }
        
        return tour_products;
    }
    class TourInfo
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

	public String search(String searchRQ)
    {
        String responseXML;
        try {
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://192.168.1.100:8181/cxf/touractivity/search");

	        StringEntity se = new StringEntity(searchRQ, HTTP.UTF_8);

	        se.setContentType("text/xml");  
	        httppost.setHeader("Content-Type","application/xml;charset=UTF-8");
	        httppost.setEntity(se);  

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
            HttpEntity responseEntity = response.getEntity();
            InputStream in = responseEntity.getContent();
            try {
                responseXML = new java.util.Scanner(in).useDelimiter("\\A").next();
            } catch (java.util.NoSuchElementException e) {
                responseXML = "";
            }
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        	responseXML = e.getMessage();
        } catch (IOException e) {
        	e.printStackTrace();
        	responseXML = e.getMessage();
		}
        return responseXML;
    } 
    
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
	 * Unmarshal this xml Message to an object.
	 * 
	 * @param xml
	 * @param system
	 * @return
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
	 * Convert a Date object to yyyy-mm-dd.
	 * @param date
	 * @return ymdDate
	 */
	public static String convertDateToYMD(Date date)
	{
			return yyyymmddDateFormat.format(date);
	}
}


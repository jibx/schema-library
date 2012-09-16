package org.jibx.android.demo.touractivity;

import java.util.Calendar;
import java.util.Date;

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
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
 
              // selected item
              String product = ((TextView) view).getText().toString();
 
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("product", product);
              startActivity(i);
          }
        });
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
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
    	String url1 = null, url2 = null, url3 = null;
        new DownloadFilesTask().execute(url1, url2, url3);
    }
    
    private class DownloadFilesTask extends AsyncTask<String, Boolean, String[][]> {
        protected String[][] doInBackground(String... urls) {
            String[][] totalSize = null;
			synchronized (Thread.currentThread())
			{
				try {
					Thread.currentThread().wait(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
            return totalSize;
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

        protected void onPostExecute(String[][] result) {
            // storing string resources into Array
            String[] tour_products = getResources().getStringArray(R.array.tour_products);
            for (String product : tour_products)
            {
            	adapter.add(product);
            }
        	progressBar.setVisibility(ProgressBar.GONE);
        	progressBar.setIndeterminate(false);
        }
    }
}

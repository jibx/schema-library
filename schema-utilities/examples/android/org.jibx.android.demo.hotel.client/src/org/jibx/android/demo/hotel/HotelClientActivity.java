package org.jibx.android.demo.hotel;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HotelClientActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        availabilityButton = (Button) findViewById(R.id.button1);
        String msg = "text:" + Html.fromHtml(getResources().getString(R.string.check_avail_button));
        Log.i(this.getClass().getName(), msg);
        availabilityButton.setText(Html.fromHtml(getResources().getString(R.string.check_avail_button)));
        targetDate = (DatePicker) findViewById(R.id.datePicker1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(ImageView.GONE);
        okayImage = (ImageView) findViewById(R.id.imageView1);
        okayImage.setVisibility(ImageView.GONE);
        cancelImage = (ImageView) findViewById(R.id.imageView2);
        cancelImage.setVisibility(ImageView.GONE);
        nights = (EditText) findViewById(R.id.editText1);
        nights.setText("3");
        status = (TextView) findViewById(R.id.textView3);
        
        // add a click listener to the button
        availabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                checkAvailability();
            }
        });
    }
    Button availabilityButton = null;    
    DatePicker targetDate = null;
    ProgressBar progressBar = null;
    ImageView okayImage = null;
    ImageView cancelImage = null;
    EditText nights = null;
    TextView status = null;
    
    public void checkAvailability()
    {
        progressBar.setVisibility(ImageView.VISIBLE);
    	okayImage.setVisibility(ImageView.GONE);
    	cancelImage.setVisibility(ImageView.GONE);
    	status.setText("Checking availability");
    	String url1 = null, url2 = null, url3 = null;
        new DownloadFilesTask().execute(url1, url2, url3);
    }
    
    private class DownloadFilesTask extends AsyncTask<String, Boolean, Long> {
        protected Long doInBackground(String... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
    			synchronized (Thread.currentThread())
    			{
    				try {
    					Thread.currentThread().wait(5 * 1000);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}
    //            totalSize += Downloader.downloadFile(urls[i]);
                publishProgress(true);
            }
            return totalSize;
        }

        protected void onProgressUpdate(Boolean... progress) {
        	progressBar.setIndeterminate(progress[0]);
        }

        protected void onPostExecute(Long result) {
            progressBar.setVisibility(ImageView.GONE);
            if (result == 0)
            {
            	okayImage.setVisibility(ImageView.VISIBLE);
            	status.setText("Rooms available");
            }
            else
            {
            	cancelImage.setVisibility(ImageView.VISIBLE);
            	status.setText("No rooms available");
            }
        }
    }
    
}
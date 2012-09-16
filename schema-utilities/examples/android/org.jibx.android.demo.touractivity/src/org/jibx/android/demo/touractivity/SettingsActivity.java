package org.jibx.android.demo.touractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Change the settings.
 * @author Don Corley <don@tourgeek.com>
 */
public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.settings_view);
 
        EditText txtHost = (EditText) findViewById(R.id.editTextHost);
        EditText txtPath = (EditText) findViewById(R.id.editTextPath);
        Button button = (Button) findViewById(R.id.buttonOkay);
 
        Intent i = getIntent();
        // getting attached intent data
        String host = i.getStringExtra(MainActivity.HOST);
        String path = i.getStringExtra(MainActivity.PATH);
        
        txtHost.setText(host);
        txtPath.setText(path);
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText txtHost = (EditText) findViewById(R.id.editTextHost);
            	EditText txtPath = (EditText) findViewById(R.id.editTextPath);

            	Intent returnIntent = new Intent();
            	returnIntent.putExtra(MainActivity.HOST, txtHost.getText().toString());
            	returnIntent.putExtra(MainActivity.PATH, txtPath.getText().toString());
            	setResult(RESULT_OK, returnIntent);     
            	 
            	finish();
            }
        });
    }
}
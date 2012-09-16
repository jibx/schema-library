package org.jibx.android.demo.touractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.product_display_view);
 
        TextView txtProduct = (TextView) findViewById(R.id.productTextView);
        TextView txtMin = (TextView) findViewById(R.id.minTextView);
        TextView txtMax = (TextView) findViewById(R.id.maxTextView);
 
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("description");
        String minPrice = i.getStringExtra("minPrice");
        String maxPrice = i.getStringExtra("maxPrice");
        // displaying selected product name
        txtProduct.setText(product);
        txtMin.setText(minPrice);
        txtMax.setText(maxPrice);
 
    }
}
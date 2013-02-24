package com.example.demo;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;

import com.example.autoupdatingdatetextview.R;
import com.widgets.autoupdatingdatetextview.AutoUpdatingDateTextView;

/**
 * @author RATTLESNAKE
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AutoUpdatingDateTextView dateTextView = (AutoUpdatingDateTextView)findViewById(R.id.date_textview);
		dateTextView.setDate(new Date());
	}
}

/**
 * 
 */
package com.widgets.autoupdatingdatetextview;

import java.util.Date;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * @author RATTLESNAKE
 *
 */
public class AutoUpdatingDateTextView extends TextView {

	private TimeAgo timeAgo;
	private Handler mHandler = new Handler();
	private long mStartTime = 0L;
	private Date date;

	public AutoUpdatingDateTextView(Context context) {
		super(context);
		init();
	}

	public AutoUpdatingDateTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AutoUpdatingDateTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	
	// Helper methods
	private void init() {
		timeAgo = new TimeAgo(this.getContext());
	}
	
	private void configureTimer() {
		if (mStartTime == 0L) {
			mStartTime = System.currentTimeMillis();
			mHandler.removeCallbacks(mUpdateTimeTask);
			mHandler.postDelayed(mUpdateTimeTask, 100);
		}
	}
	
	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {	    
			AutoUpdatingDateTextView.this.setText(timeAgo.timeAgo(date));


			final long start = mStartTime;
			long elapseTime = System.currentTimeMillis() - start;

			int seconds = (int) (elapseTime / 1000);
			//int minutes = seconds / 60;
			seconds     = seconds % 60;

			// add a delay to adjust for computation time
			long delay = (1000 - (elapseTime%1000));

			mHandler.postDelayed(this, delay);
		}
	};


	// Overrided or superview methods
	@Override
	protected void onDetachedFromWindow() {
		mHandler.removeCallbacks(mUpdateTimeTask);
		mHandler = null;
		
		mStartTime = 0L;		
		timeAgo = null;
		date = null;

		super.onDetachedFromWindow();
	}


	// Properties: Getter-Setter methods
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;

		mStartTime = 0L;
		configureTimer();
	}
}

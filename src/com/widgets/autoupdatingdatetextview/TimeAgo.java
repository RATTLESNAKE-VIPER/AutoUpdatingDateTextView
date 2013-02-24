package com.widgets.autoupdatingdatetextview;

import java.util.Date;

import android.content.Context;
import android.content.res.Resources;

import com.example.autoupdatingdatetextview.R;

/**
 * @author RATTLESNAKE
 *
 */
public class TimeAgo {

	protected Context context;

	public TimeAgo(Context context) {
		this.context = context;
	}

	public String timeAgo(Date date) {
		return timeAgo(date.getTime());
	}

	public String timeAgo(long millis) {
		long diff = new Date().getTime() - millis;

		Resources r = context.getResources();

		String prefix = r.getString(R.string.time_ago_prefix);
		String suffix = r.getString(R.string.time_ago_suffix);

		double seconds = Math.abs(diff) / 1000;
		double minutes = seconds / 60;
		double hours = minutes / 60;
		double days = hours / 24;		
		double years = days / 365;

		String words;

		if(seconds < 5) {
			words = r.getString(R.string.time_ago_second);
			suffix = null;
	    } else if(seconds < 60) {
			words = r.getString(R.string.time_ago_seconds, Math.round(seconds));
	    } else if(seconds < 120) {
			words = r.getString(R.string.time_ago_minute, 1);
	    } else if (minutes < 60) {
			words = r.getString(R.string.time_ago_minutes, Math.round(minutes));
	    } else if (minutes < 120) {
			words = r.getString(R.string.time_ago_hour, 1);
	    } else if (hours < 24) {
			words = r.getString(R.string.time_ago_hours, Math.round(hours));
	    } else if (days < 2) {
			words = r.getString(R.string.time_ago_day, 1);
			suffix = null;
	    } else if (days < 7) {
			words = r.getString(R.string.time_ago_days, Math.round(days));
	    } else if (days < 14) {
			words = r.getString(R.string.time_ago_week, 1);
			suffix = null;
	    } else if (days < 31) {
			words = r.getString(R.string.time_ago_weeks, Math.round(days / 7));
		} else if (days < 61) {
			words = r.getString(R.string.time_ago_month, 1);
			suffix = null;
	    } else if (days < 365) {
			words = r.getString(R.string.time_ago_months, Math.round(days / 30));
	    } else if (years < 2) {
	    	words = r.getString(R.string.time_ago_year, 1);
	    	suffix = null;
	    } else {
			words = r.getString(R.string.time_ago_years, Math.round(years));
		}

		StringBuilder sb = new StringBuilder();

		if (prefix != null && prefix.length() > 0) {
			sb.append(prefix).append(" ");
		}

		sb.append(words);

		if (suffix != null && suffix.length() > 0) {
			sb.append(" ").append(suffix);
		}

		return sb.toString().trim();
	}
}
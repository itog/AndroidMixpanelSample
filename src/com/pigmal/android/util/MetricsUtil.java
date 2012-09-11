package com.pigmal.android.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mixpanel.android.mpmetrics.MPMetrics;

public class MetricsUtil {
	String mToken;
	static MPMetrics mMPMetrics;

	public static void init(Context c, String token) {
		mMPMetrics = MPMetrics.getInstance(c, token);
	}

	public static void track(String event, String[] properties) {
		if (mMPMetrics == null) return;
		
		JSONObject prop = new JSONObject();
		try {
			for (int i = 0; i < properties.length/2; i++) {
				prop.put(properties[i*2], properties[i*2 + 1]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mMPMetrics.track(event, prop);
	}
}

package com.pigmal.android.util;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.pigmal.android.sample.mixpanel.R;

public class Config {
	static final String TAG_NAME_MIXPANEL = "mixpanel";
	static final String ATTR_NAME_TOKEN = "token";
	
	private static String mMixpanelToken;
	
	public static void parse(Context c, int id) {
		XmlResourceParser xmlParser = c.getResources().getXml(R.xml.config);

		try {
			xmlParser.next();
			int eventType = xmlParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xmlParser.getName().equals(TAG_NAME_MIXPANEL)) {
						for (int i = 0; i < xmlParser.getAttributeCount(); i++) {
							if (xmlParser.getAttributeName(i).equals(ATTR_NAME_TOKEN)) {
								mMixpanelToken = xmlParser.getAttributeValue(i);
							}
						}
					}
				}
				eventType = xmlParser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMixpanelToken() {
		return mMixpanelToken;
	}
}

package com.pigmal.android.sample.mixpanel;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.pigmal.android.util.Config;
import com.pigmal.android.util.MetricsUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	static final String TAG = "MixpanelSample";
	
	Button button1;
	Button button2;
	Button button3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		Config.parse(this, R.xml.config);
		if (Config.getMixpanelToken() != null && !Config.getMixpanelToken().equals("")) {
			MetricsUtil.init(this, Config.getMixpanelToken());
		} else {
			Log.e(TAG, "failed to get mixpanel token");
		}
		
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.button1:
			MetricsUtil.track("click", new String[] {"id", "button1"});
			break;
		case R.id.button2:
			MetricsUtil.track("click", new String[] {"id", "button2"});
			break;
		case R.id.button3:
			MetricsUtil.track("click", new String[] {"id", "button3"});
			break;
		default:
			break;
		}
	}
}

package com.example.bbs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {
	private final static String TAG = AboutActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		getActionBar().setTitle("AmoMcu");

		Intent intent = getIntent();
		String value = intent.getStringExtra("testIntent");

		String help = "";

		TextView tv = (TextView) findViewById(R.id.about_text_help);
		tv.setText(help);
	}

	public void onClick(View v) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
	}

}
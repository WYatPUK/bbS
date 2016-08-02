package com.example.bbs;

import com.google.zxing.client.android.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_bbs extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_bbs);
		findViewById(R.id.button_Scan).setOnClickListener(this);
		findViewById(R.id.button_Link).setOnClickListener(this);
		findViewById(R.id.button_Decode).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_Scan:
			Intent intent2 = new Intent(this, CaptureActivity.class);
			startActivityForResult(intent2, 1);
			break;
		case R.id.button_Link:
			Intent intent = new Intent(this, DeviceScanActivity.class);
			startActivity(intent);
			break;
		case R.id.button_Decode:
			Intent intent3 = new Intent(this, Decode.class);
			startActivity(intent3);
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			//TextView scanResult = (TextView) findViewById(R.id.scan_result);
			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("scan_result");
				Intent intent = new Intent (this, Show.class);
				startActivity(intent);
			} else if (resultCode == RESULT_CANCELED) {
			}
			break;
		}
	}

}

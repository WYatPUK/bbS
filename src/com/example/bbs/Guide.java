package com.example.bbs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Guide extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		Thread thread = new Thread(new Runnable() 
		{
				@Override
				public void run()
				{
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Go_Next();
				}
		});
		thread.start();
		
	}
	
	private void Go_Next() {
		Intent intent = new Intent(this, Main_bbs.class);
		startActivity(intent);
		finish();
	}

}

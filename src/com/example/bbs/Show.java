package com.example.bbs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Show extends Activity {

	private String Code;
	private TextView text;
	private TextView Info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		Bundle bundle = getIntent().getExtras();
		Code = bundle.getString("Code");
		text = (TextView)this.findViewById(R.id.Main_Text);
		Info = (TextView)this.findViewById(R.id.Main_Info);
		if (!Function.Is_Legel(Code)) {
			text.setText("命令非法");
			Info.setText("请确认二维码来源，或重新扫描二维码再试\n如有疑问请联系作者个人邮箱377545660@qq.com");
		}
		else {
			text.setText("已识别命令");
			
		}
	}
}

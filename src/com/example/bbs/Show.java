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
			text.setText("����Ƿ�");
			Info.setText("��ȷ�϶�ά����Դ��������ɨ���ά������\n������������ϵ���߸�������377545660@qq.com");
		}
		else {
			text.setText("��ʶ������");
			
		}
	}
}

package com.example.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_tz extends Activity {
	private Button tz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tz);
        tz = (Button) findViewById(R.id.btn_tz);
        tz.setOnClickListener(new View.OnClickListener() {
			
        	//��תҳ��
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(Activity_tz.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
}

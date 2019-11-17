package com.example.androidgame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	 private TextView tvScore;
	 private Button music_turnoff;
	 
	    private int score=0;
	    private static MainActivity mainActivity=null;
	    public static MainActivity getMainActivity() {
	        return mainActivity;
	    }
	    public MainActivity(){
	        mainActivity=this;
	    }
	    
	    //������������㣩
	    public void clearScore(){
	        score=0;
	        showScore();
	    }
	    
	    //չʾ��������ʾ��
	    public void showScore(){
	        tvScore.setText(score+"");
	    }
	    
	    //��������
	    public void addScore(int s){
	        score+=s;
	        showScore();
	    }
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //�ҵ��Ʒְ�
	        tvScore= (TextView) findViewById(R.id.score);
	        //���ֲ��Ű�ť
	        music_turnoff = (Button) findViewById(R.id.turn_off_music);
	        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.wuyueyu);
	        
	        music_turnoff.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					 if(mp3.isPlaying() == true)
	                        // Pause the music player
	                        mp3.pause();
	                    
	                    else
	                        mp3.start();
	                }	
			});
	    }
	   
}

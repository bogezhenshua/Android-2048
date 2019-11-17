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
	    
	    //清除分数（归零）
	    public void clearScore(){
	        score=0;
	        showScore();
	    }
	    
	    //展示分数（显示）
	    public void showScore(){
	        tvScore.setText(score+"");
	    }
	    
	    //分数增加
	    public void addScore(int s){
	        score+=s;
	        showScore();
	    }
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //找到计分板
	        tvScore= (TextView) findViewById(R.id.score);
	        //音乐播放按钮
	        music_turnoff = (Button) findViewById(R.id.turn_off_music);
	        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.wuyueyu);
	        
	        music_turnoff.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					 if(mp3.isPlaying() == true)
	                        // Pause the music player
	                        mp3.pause();
	                    
	                    else
	                        mp3.start();
	                }	
			});
	    }
	   
}

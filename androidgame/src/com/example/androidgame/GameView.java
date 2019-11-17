package com.example.androidgame;

import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {
	private Card[][] cardsMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();
	//���췽��
    public GameView(Context context) {
        super(context);
        initGameView();
    }
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }
    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }
    //��ʼ������
    private void initGameView(){
        setColumnCount(4);
        //�¼�����
        setOnTouchListener(new OnTouchListener() {
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX()-startX;
                        offsetY = motionEvent.getY()-startY;

                        if(Math.abs(offsetX)>Math.abs(offsetY)){
                            if(offsetX>5){
//                                System.out.println("right");
                                swipeRight();
                            }else if(offsetX<-5){
//                                System.out.println("left");
                                swipeLeft();
                            }
                        }else{
                            if(offsetY>5){
//                                System.out.println("down");
                                swipeDown();
                            }else if(offsetY<-5){
//                                System.out.println("up");
                                swipeUp();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
    private void startGame(){
    	boolean bl=false;
    	if(bl==true)
    	{
    		MainActivity.getMainActivity().clearScore();
    		bl=false;
    	}
    		//����
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
        bl=true;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int viewWidth = (Math.min(w,h)-10)/4;
        addCards(viewWidth,viewWidth);
        startGame();
    }
    private void addCards(int viewWidth,int viewHeight){
        Card c;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,viewWidth,viewHeight);
                cardsMap[x][y] = c;
            }
        }
    }
    private void addRandomNum(){
        emptyPoints.clear();
        //���ҿյĿ�Ƭ
        for(int y=0;y<4;y++) {
            for (int x = 0; x < 4; x++) {
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        //��ӿ�Ƭ
        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()<=0.9?2:4);
    }
    private void checkComplete(){
        boolean complete = true;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))) {
                    complete = false;
                    break;
                }
            }
        }
        if (complete) {
//            new AlertDialog.Builder(getContext()).setTitle("���").setMessage("��Ϸ����").setPositiveButton("����", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    startGame();
//                }
//            }).show();
        	AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
			dialog.setTitle("Game Over��");
			dialog.setMessage("��̫���ˣ���Ҫ����һ��ô��");
			dialog.setCancelable(false);
			dialog.setPositiveButton("���¿�ʼ",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					MainActivity.getMainActivity().clearScore();
					startGame();
				}
			});
			dialog.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();
        }
    }
    private void swipeLeft(){
        boolean merge=false;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                //�������ұ���
                for(int x1=x+1;x1<4;x1++) {
                    if(cardsMap[x1][y].getNum()>0) {
                        if (cardsMap[x1][y].getNum() == cardsMap[x][y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            merge=true;
                            //�ӷ�
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                        } else if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge==true){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeRight(){
        boolean merge=false;
        for(int y=0;y<4;y++){
            for(int x=3;x>=0;x--){
                for(int x1=x-1;x1>=0;x1--) {
                    if(cardsMap[x1][y].getNum()>0) {
                        if (cardsMap[x1][y].getNum() == cardsMap[x][y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            //�ӷ�
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge=true;
                        } else if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge==true){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeUp(){
        boolean merge=false;
        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++) {
                    if(cardsMap[x][y1].getNum()>0) {
                        if (cardsMap[x][y1].getNum() == cardsMap[x][y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            //�ӷ�
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            cardsMap[x][y1].setNum(0);
                            merge=true;
                        } else if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge==true){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeDown(){
        boolean merge=false;
        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y--){
                for(int y1=y-1;y1>=0;y1--) {
                    if(cardsMap[x][y1].getNum()>0) {
                        if (cardsMap[x][y1].getNum() == cardsMap[x][y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            //�ӷ�
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            cardsMap[x][y1].setNum(0);
                            merge=true;
                        } else if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge==true){
            addRandomNum();
            checkComplete();
        }
    }
}

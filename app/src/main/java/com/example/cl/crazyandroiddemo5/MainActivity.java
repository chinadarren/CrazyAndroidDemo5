package com.example.cl.crazyandroiddemo5;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 霓虹灯效果
 */
public class MainActivity extends Activity {
private int currentColor = 0;
    //定义一个颜色数组
    final int [] colors = new int []{
            android.R.color.holo_red_dark,
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_bright,
            android.R.color.black,
            };
    final int [] names = new int[]{
            R.id.view01,
            R.id.view02,
            R.id.view03,
            R.id.view04,
            R.id.view05,
            R.id.view06};
    TextView[] views = new TextView[names.length];
    Handler handler = new Handler(){
        @Override
    public void handleMessage(Message msg){
            //表明消息来自本程序所发
            if (msg.what == 0x123){
                for (int i = 0; i<names.length; i++){
                    views[i].setBackgroundResource(colors[(i + currentColor) % names.length ]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_demo);
         for(int i = 0; i < names.length; i ++){
             views[i] = (TextView) findViewById(names[i]);
         }
         //定义一个线程周期性地改变currentColor变量值
         new Timer().schedule(new TimerTask() {
             @Override
             public void run() {
                 //发送一条空消息通知系统改变6个TextView组件的背景颜色
                 handler.sendEmptyMessage(0x123);
             }
         },0,200);
    }
}

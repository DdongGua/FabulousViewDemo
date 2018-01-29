package com.example.fabulousviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tyrantgit.widget.HeartLayout;

/**
 * @author 亮亮
 */
public class MainActivity extends AppCompatActivity {

    private HeartLayout heartLayout;
    //定义一个颜色的随机数
    private Random random = new Random();
    //定义一个时间的定时器
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartLayout = findViewById(R.id.heartlayout);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //不能再子线程中更新ui
                heartLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        heartLayout.addHeart(randomColor());
                    }
                });

            }
        }, 500, 200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private int randomColor() {
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}

package com.rku.tutorial9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        Timer timer =new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent intent =new Intent(splash_screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        timer.schedule(timerTask,1000);
    }
}
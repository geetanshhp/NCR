package com.example.adcitymart;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class splash_screen extends AppCompatActivity
{
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        imageView=findViewById(R.id.splash);
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
thread.start();

    }

}
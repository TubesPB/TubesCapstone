package com.capstone.tubescapstone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private int waktu_loading = 4000; //4000 = 4 detik (Waktu SplashScreen)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent intent = new Intent(com.capstone.tubescapstone.SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },waktu_loading);

    }
}
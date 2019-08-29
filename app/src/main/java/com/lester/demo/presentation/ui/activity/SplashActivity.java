package com.lester.demo.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.lester.demo.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToHome();
            }
        }, 2500);
    }

    private void goToHome() {
        startActivity(new Intent(this, HomeUserActivity.class));
        finish();
    }
}
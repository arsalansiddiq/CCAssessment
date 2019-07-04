package com.mobile.ccassessment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.weather.ui.weather.WeatherActivity;
import com.mobile.restaruant.databinding.ActivityRestaurantBinding;


public class SplashActivity extends AppCompatActivity {

    ActivityRestaurantBinding activityRestaurantBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        try {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, WeatherActivity.class));
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

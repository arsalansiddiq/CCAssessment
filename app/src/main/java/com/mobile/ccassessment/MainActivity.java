package com.mobile.ccassessment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.restaruant.ui.restsurant.RestaurantActivity;
import com.mobile.restaruant.ui.weather.WeatherActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        try {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

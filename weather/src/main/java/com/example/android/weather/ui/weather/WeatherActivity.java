package com.example.android.weather.ui.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.android.weather.R;
import com.example.android.weather.data.APIResponseWeather;
import com.example.android.weather.data.network.model.response.weather.List;
import com.example.android.weather.data.network.model.response.weather.WeatherResponse;
import com.example.android.weather.databinding.ActivityWeatherBinding;
import com.example.android.weather.utils.LocationListener;
import com.example.android.weather.utils.configuration.Constants;
import com.example.android.weather.viewmodels.WeatherViewModel;
import com.mobile.restaruant.ui.restsurant.RestaurantActivity;

import java.util.Calendar;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    com.example.android.weather.data.network.model.response.weather.List list = new List();

    ActivityWeatherBinding binding;
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        binding.setWeatherViewModel(weatherViewModel);
        binding.setLifecycleOwner(this);


        binding.btnRestaurants.setOnClickListener(view ->
            startActivity(new Intent(WeatherActivity.this, RestaurantActivity.class)));
        if (isNetworkConnected()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                getLocationUpdates();
            }

        } else {
            Toast.makeText(this, "please check your internet!", Toast.LENGTH_SHORT).show();
        }

        weatherViewModel.getWeather().observe(this, new Observer<APIResponseWeather>() {
            @Override
            public void onChanged(@Nullable APIResponseWeather apiResponseWeather) {
                consumeResponseWeather(apiResponseWeather);
            }
        });

    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void getLocationUpdates() {
        LocationListener.getInstance(this.getApplicationContext()).observe((LifecycleOwner) com.example.android.weather.ui.weather.WeatherActivity.this, new Observer<Location>() {
            @Override
            public void onChanged(@androidx.annotation.Nullable Location location) {
                if (location != null) {
                    Log.i("LocationCoor ", location.getLatitude() +" " + location.getLongitude());
                    LocationListener.getInstance(com.example.android.weather.ui.weather.WeatherActivity.this.getApplicationContext()).removeObserver(this);
                    weatherViewModel.getWeatherForecast(location.getLatitude(),location.getLongitude());
                } else
                    Log.i("LocationCoor ", "null");
            }
        });
    }

    private void consumeResponseWeather(APIResponseWeather apiResponseWeather) {
        switch (apiResponseWeather.status) {

            case LOADING:
                Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show();
                break;

            case SUCCESS:
                renderSuccessResponseWeather(apiResponseWeather.data);
                break;

            case ERROR:
                Toast.makeText(this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @SuppressLint("WrongConstant")
    private void renderSuccessResponseWeather(WeatherResponse data) {
        if (data != null) {
            Log.d("response=", data.toString());
            binding.recyclerViewWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            list = weatherViewModel.formatList(data.getList()).get(0);
            binding.txtViewCity.setText(data.getCity().getName());
            binding.txtViewDay.setText(Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK,
                    Calendar.LONG_FORMAT, Locale.US));
//            binding.txtViewDay.setText(weatherViewModel.formatDay(list.getDt()));
            String url = Constants.OPEN_WEATHER_ICON_BASE_URL + list.getWeather().get(0).getIcon() + ".png";
            Glide.with(this).load(url).into(binding.imageView);
            Integer temp = (int) Math.round(list.getMain().getTemp());
            binding.txtViewTemp.setText(temp.toString() );
            AdapterWeather adapter =
                    new AdapterWeather(weatherViewModel.formatList(data.getList()), weatherViewModel,this);
            binding.recyclerViewWeather.setAdapter(adapter);
        } else {
            Toast.makeText(this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "granted!", Toast.LENGTH_SHORT).show();
            getLocationUpdates();
        } else {
            Toast.makeText(this, "not granted!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:

                    moveTaskToBack(true);

                    return true;
            }
            return false;
    }
}
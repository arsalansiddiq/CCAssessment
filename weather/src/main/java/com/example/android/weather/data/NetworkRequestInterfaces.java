package com.example.android.weather.data;

import com.example.android.weather.data.network.model.response.weather.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkRequestInterfaces {

    @GET("data/2.5/forecast")
    Observable<WeatherResponse> getForecast(
            @Query("lat") Double lat,
            @Query("lon") Double lon,
            @Query("APPID") String APPID
    );
}
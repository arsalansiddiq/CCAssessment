package com.example.android.weather.data;

import com.example.android.weather.data.network.model.response.weather.WeatherResponse;
import com.example.android.weather.utils.configuration.Constants;

import io.reactivex.Observable;

import static com.example.android.weather.utils.configuration.Constants.APPID_OPENWEATHER;


public class NetworkCalls {

    public NetworkCalls(){}

    private final String LOG_TAG = NetworkCalls.class.getName();

    private static NetworkRequestInterfaces sNetworkRequestInterfaces = APIUtils.getConnection(Constants.BASE_URL_OPENWEATHER);

    public Observable<WeatherResponse> getForecast(double lat, double lon) {
        return sNetworkRequestInterfaces.getForecast(lat,lon, APPID_OPENWEATHER);
    }

}

package com.mobile.restaruant.data;

import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;
import com.mobile.restaruant.data.network.model.response.weather.WeatherResponse;
import com.mobile.restaruant.utils.configuration.Constants;

import io.reactivex.Observable;

import static com.mobile.restaruant.utils.configuration.Constants.APPID_OPENWEATHER;


public class NetworkCalls {

    public NetworkCalls(){}

    private final String LOG_TAG = NetworkCalls.class.getName();

    private static NetworkRequestInterfaces sNetworkRequestInterfaces = APIUtils.getConnection(Constants.BASE_URL_OPENWEATHER);
    private static NetworkRequestInterfaces sNetworkRequestInterfacesRestaurant = APIUtils.getConnection(Constants.BASE_URL_RESTAURANT);

    public Observable<RestaurantResponse> getRestaurants(String latlng) {
        return sNetworkRequestInterfacesRestaurant.getNearBy(latlng,1500,"restaurant",Constants.APPID_RESTAURANT);
    }

    public Observable<WeatherResponse> getForecast(double lat, double lon) {
        return sNetworkRequestInterfaces.getForecast(lat,lon, APPID_OPENWEATHER);
    }

}

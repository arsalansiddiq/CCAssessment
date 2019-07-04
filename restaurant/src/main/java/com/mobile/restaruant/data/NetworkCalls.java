package com.mobile.restaruant.data;

import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;
import com.mobile.restaruant.utils.configuration.Constants;

import io.reactivex.Observable;


public class NetworkCalls {

    public NetworkCalls(){}

    private final String LOG_TAG = NetworkCalls.class.getName();

    private static NetworkRequestInterfaces sNetworkRequestInterfacesRestaurant = APIUtils.getConnection(Constants.BASE_URL_RESTAURANT);

    public Observable<RestaurantResponse> getRestaurants(String latlng) {
        return sNetworkRequestInterfacesRestaurant.getNearBy(latlng,1500,"restaurant",Constants.APPID_RESTAURANT);
    }

}

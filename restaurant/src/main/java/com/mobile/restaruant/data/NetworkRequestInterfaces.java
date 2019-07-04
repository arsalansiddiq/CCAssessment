package com.mobile.restaruant.data;



import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkRequestInterfaces {

    @GET("place/nearbysearch/json")
    Observable<RestaurantResponse> getNearBy(
            @Query("location") String location,
            @Query("radius") Integer radius,
            @Query("type") String type,
            @Query("key") String key
    );
}
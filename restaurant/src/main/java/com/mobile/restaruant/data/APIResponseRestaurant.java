package com.mobile.restaruant.data;

import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.mobile.restaruant.data.Status.ERROR;
import static com.mobile.restaruant.data.Status.LOADING;

public class APIResponseRestaurant {

    public Status status;

    @Nullable
    public RestaurantResponse data;

    @Nullable
    public Throwable error;

    private APIResponseRestaurant(Status status, @Nullable RestaurantResponse data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public APIResponseRestaurant() {
    }

    public static APIResponseRestaurant loading() {
        return new APIResponseRestaurant(LOADING, null, null);
    }

    public static APIResponseRestaurant success(@NonNull RestaurantResponse data) {
        return new APIResponseRestaurant(Status.SUCCESS, data, null);
    }

    public static APIResponseRestaurant error(@NonNull Throwable error) {
        return new APIResponseRestaurant(ERROR, null, error);
    }
}

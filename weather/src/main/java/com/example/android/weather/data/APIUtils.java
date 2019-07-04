package com.example.android.weather.data;

import com.example.android.weather.data.network.singleton.APIClient;

public class APIUtils {

    private APIUtils() {

    }

    public static NetworkRequestInterfaces getConnection(String baseURL) {
        return APIClient.getRetrofitClient(baseURL).create(NetworkRequestInterfaces.class);
    }
}

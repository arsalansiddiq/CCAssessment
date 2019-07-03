package com.mobile.restaruant.data;


import com.mobile.restaruant.data.network.singleton.APIClient;

public class APIUtils {

    private APIUtils() {

    }

    public static NetworkRequestInterfaces getConnection(String baseURL) {
        return APIClient.getRetrofitClient(baseURL).create(NetworkRequestInterfaces.class);
    }
}

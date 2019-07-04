package com.example.android.weather.data.network.singleton;

import com.example.android.weather.data.NetworkCalls;

public class NetworkSingleton {

    private static NetworkCalls sNetworkCalls;

    public static NetworkCalls getNetworkCallInstance() {
        if (sNetworkCalls == null)
        {
            sNetworkCalls = new NetworkCalls();
        }

        return sNetworkCalls;
    }



}

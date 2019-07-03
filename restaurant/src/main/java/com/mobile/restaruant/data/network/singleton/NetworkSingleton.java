package com.mobile.restaruant.data.network.singleton;


import com.mobile.restaruant.data.NetworkCalls;

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

package com.mobile.restaruant.data.network.singleton;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit sRetrofit;

    //Call`s response waiting
    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    public static Retrofit getRetrofitClient (String baseURL) {

//        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
//        }
        return sRetrofit;
    }
    public static void canceler () {
        if (sRetrofit != null) {
            sOkHttpClient.dispatcher().cancelAll();
        }
    }


}

package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class NetworkModule {
    private static final NetworkModule ourInstance = new NetworkModule();

    final private long DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 4000;

    final private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .build();

    final private Moshi mMoshi = new Moshi.Builder().build();

    final private Retrofit mRetrofit = new Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(mMoshi))
            .client(mOkHttpClient)
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .build();

    public static NetworkModule getInstance() {
        return ourInstance;
    }

    final public DirectionsService directionsService = new DirectionsService(mRetrofit.create(DirectionsApi.class));
}

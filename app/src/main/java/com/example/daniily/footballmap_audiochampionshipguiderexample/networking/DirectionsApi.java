package com.example.daniily.footballmap_audiochampionshipguiderexample.networking;

import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models.Routes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

interface DirectionsApi {

    @GET("directions/json?")
    Call<Routes> getDirection(@Query("origin") String origin,
                              @Query("destination") String destination,
                              @Query("mode") String mode,
                              @Query("key") String apiKey);
}

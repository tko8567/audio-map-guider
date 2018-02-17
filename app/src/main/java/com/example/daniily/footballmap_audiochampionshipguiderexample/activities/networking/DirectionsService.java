package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class DirectionsService {
    private DirectionsApi api;

    DirectionsService(DirectionsApi api) {
        this.api = api;
    }

    public void getPolyline(String origin, String destination, CallbackOnResponse callback) {
        enqueue(api.getDirection(origin, destination, "AIzaSyB6ZhhKeZaoUUxaQL11jEVzQ-ASur-QV60"), callback);
    }

    private <T> void enqueue(Call<T> call, final CallbackOnResponse callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.body() != null) callback.onResponseSuccess(response);
                else {
                    callback.onResponseError("server_error");
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onResponseError("error203");
            }
        });
    }

}

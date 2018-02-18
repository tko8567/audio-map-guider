package com.example.daniily.footballmap_audiochampionshipguiderexample.networking;

import com.google.android.gms.maps.model.LatLng;

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

    public void getPolyline(LatLng origin, LatLng destination, CallbackOnResponse callback) {

        String originStr = String.valueOf(origin.latitude)+","+String.valueOf(origin.longitude);
        String destinationStr = String.valueOf(destination.latitude)+","+String.valueOf(destination.longitude);

        enqueue(api.getDirection(originStr, destinationStr, "AIzaSyB6ZhhKeZaoUUxaQL11jEVzQ-ASur-QV60"), callback);
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
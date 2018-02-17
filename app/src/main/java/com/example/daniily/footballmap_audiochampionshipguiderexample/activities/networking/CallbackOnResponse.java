package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking;

import retrofit2.Response;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public interface CallbackOnResponse {
    void onResponseSuccess(Response response);

    void onResponseError(String error);

    default void onResponseError(String error, String msg) {
        onResponseError(error);
    }
}

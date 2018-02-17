package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Vesbat on 17.02.2018.
 */

public class Routes {
    @Json(name = "routes")
    private List<Rout> routs;

    public List<Rout> getRouts() {
        return routs;
    }
}

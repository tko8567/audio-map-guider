package com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class Routes {
    @Json(name = "routes")
    private List<Rout> routs;

    public ArrayList<LatLng> getCoordinates() {
        ArrayList<LatLng> coordinates = new ArrayList<>();

        List<Leg> legs = routs.get(0).getLegs();
        for (Leg leg : legs) {
            List<Step> steps = leg.getSteps();
            for (Step step : steps) {
                coordinates.add(step.getEndLocation());
            }
        }

        return coordinates;
    }
}

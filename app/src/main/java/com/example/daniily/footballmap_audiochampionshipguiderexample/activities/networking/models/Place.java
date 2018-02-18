package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.Constants;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Dmitrii Polyakov on KENIGHACK 2018.
 */

public class Place {

    ArrayList<PlaceLocate> locates = new ArrayList<>();
    String audio;
    LatLng coordinates;

    public Place(PlaceLocate placeLocate, LatLng coordinates, String audio) {
        locates.add(placeLocate);
        this.coordinates = coordinates;
        this.audio = audio;
    }

    public void addLocate(PlaceLocate placeLocate){
        locates.add(placeLocate);
    }

    public String getAudio(Constants.Localization localization){
        return localization.toString().toLowerCase() + "/" + audio;
    }

    public String getName(Constants.Localization localization){
        for (PlaceLocate locate : locates){
            if (locate.localization == localization){
                return locate.name;
            }
        }
        return locates.get(0).name;
    }

    public String getDescription(Constants.Localization localization){
        for (PlaceLocate locate : locates){
            if (locate.localization == localization){
                return locate.description;
            }
        }
        return locates.get(0).description;
    }

    public static class PlaceLocate {
        Constants.Localization localization;
        String name;
        String description;

        public PlaceLocate(Constants.Localization localization, String name, String description) {
            this.localization = localization;
            this.name = name;
            this.description = description;
        }
    }
}

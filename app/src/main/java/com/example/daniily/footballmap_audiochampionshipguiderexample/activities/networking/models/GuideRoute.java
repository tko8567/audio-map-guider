package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.networking.models;

import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.Constants;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksey on 18.02.18.
 */

public class GuideRoute {
    private static List<Place> points = new ArrayList<>();
    static {
        Place.PlaceLocate locate = new Place.PlaceLocate(Constants.Localization.RUS, "Кафедральный собор", "");
        points.add(new Place(locate, new LatLng(54.706383, 20.512047), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Художественная галерея", "");
        points.add(new Place(locate, new LatLng(54.708498, 20.5199), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Закхаймские ворота", "");
        points.add(new Place(locate, new LatLng(54.709593, 20.538291), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Королевские ворота", "");
        points.add(new Place(locate, new LatLng(54.713761, 20.535796), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Казарма Кронпринц", "");
        points.add(new Place(locate, new LatLng(54.717001, 20.531291), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Памятник Василевскому", "");
        points.add(new Place(locate, new LatLng(54.720838, 20.523863), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Россгартенские ворота", "");
        points.add(new Place(locate, new LatLng(54.722051, 20.523815), ""));

        locate = new Place.PlaceLocate(Constants.Localization.RUS, "Музей янтаря", "");
        points.add(new Place(locate, new LatLng(54.722256, 20.522989), ""));

    }

    public static List<Place> get() {
        return points;
    }

    private GuideRoute() {
    }
}

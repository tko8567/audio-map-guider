package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniily on 2/18/18.
 */

public class SharedPreferencesFiller extends Activity {


    private static final String TAG = "SharedPreferencesFiller";

    private static final String SHARED_PREFERENCES_ROUTES = "routes.json";

    private static final String ROUTES = "routes";
    private static final String ROUTE = "route";
    private static final String TOKEN = "token";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static String OBJECT = "_object";
    private static String ID = "id";
    private static String NAME = "name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//            /*

//            Россгартенские ворота 54.722051, 20.523815
//            Памятник Василевскому 54.720838, 20.523863
//            Казарма Кронпринц 54.717001, 20.531291
//            Королевские ворота 54.713761, 20.535796
//            Закхаймские ворота 54.709593, 20.538291

//            Художественная галерея 54.708498, 20.5199
//            Кафедральный собор 54.706383, 20.512047
//            Музей янтаря 54.722256, 20.522989
//             */

        try {

            JSONObject map = new JSONObject(
                    "{\"_object\":\"RouteList\",\"name\":\"Route set\",\"routes\":[{\"id\":0,\"_object\":\"Route\",\"name\":\"@routeName_greatPrusses\",\"landmarks\":[{\"_object\":\"Landmark\",\"name\":\"@landmark_RosgartenersGate\",\"lat\":54.722051,\"lng\":20.523815},{\"_object\":\"Landmark\",\"name\":\"@landmark_VasilevskiyMonument\",\"lat\":54.720838,\"lng\":20.523863},{\"_object\":\"Landmark\",\"name\":\"@landmark_KronprinzBarracks\",\"lat\":54.717001,\"lng\":20.531291},{\"_object\":\"Landmark\",\"name\":\"@landmark_KingsGate\",\"lat\":54.713761,\"lng\":20.535796},{\"_object\":\"Landmark\",\"name\":\"@landmark_SackheimGate\",\"lat\":54.709593,\"lng\":20.538291}]}]}"
            );

            getSharedPreferences(SHARED_PREFERENCES_ROUTES, MODE_PRIVATE).edit()
                    .clear()
                    .putString(ROUTES, map.toString())
                    .apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }
}

/*
Produced JSON:
{
  "_object" : "RouteList",
  "name" : "Route set",
  "routes": [

    {
      "id": 0,
      "_object" : "Route",
      "name" : "@routeName_greatPrusses",
      "landmarks" : [

        {
          "_object" : "Landmark",
          "name" : "@landmark_RosgartenersGate",
          "lat": 54.722051,
          "lng": 20.523815
        },

        {
          "_object" : "Landmark",
          "name" : "@landmark_VasilevskiyMonument",
          "lat": 54.720838,
          "lng": 20.523863
        },


        {
          "_object" : "Landmark",
          "name" : "@landmark_KronprinzBarracks",
          "lat": 54.717001,
          "lng": 20.531291
        },


        {
          "_object" : "Landmark",
          "name" : "@landmark_KingsGate",
          "lat": 54.713761,
          "lng": 20.535796
        },


        {
          "_object" : "Landmark",
          "name" : "@landmark_SackheimGate",
          "lat": 54.709593,
          "lng": 20.538291
        }
      ]
    }
  ]
}
 */

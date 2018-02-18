package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daniily on 2/18/18.
 */

public class SharedPreferencesFiller extends Activity {

    private final static double[] dest1 = {54.706383, 20.512047};
    private final static double[] dest2 = {54.706634, 20.500288};

    private static final String TAG = "SharedPreferencesFiller";

    private static final String ROUTE = "route";
    private static final String TOKEN = "token";
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            JSONObject dest1 = new JSONObject();
            JSONObject dest2 = new JSONObject();
            JSONObject dest3 = new JSONObject();
            JSONObject dest4 = new JSONObject();
            JSONObject dest5 = new JSONObject();
            JSONObject dest6 = new JSONObject();
            JSONObject dest7 = new JSONObject();
            JSONObject dest8 = new JSONObject();

            dest1
                    .put(TOKEN, "dest1")
                    .put(LAT, 54.706383)
                    .put(LNG, 20.512047);
            dest2
                    .put(TOKEN, "dest2")
                    .put(LAT, 54.708498)
                    .put(LNG, 20.5199);
            dest3
                    .put(TOKEN, "dest3")
                    .put(LAT, 54.709593)
                    .put(LNG, 20.538291);
            dest4
                    .put(TOKEN, "dest4")
                    .put(LAT, 54.713761)
                    .put(LNG, 20.535796);
            dest5
                    .put(TOKEN, "dest5")
                    .put(LAT, 54.717001)
                    .put(LNG, 20.531291);
            dest6
                    .put(TOKEN, "dest6")
                    .put(LAT, 54.720838)
                    .put(LNG, 20.523863);
            dest7
                    .put(TOKEN, "dest7")
                    .put(LAT, 54.722051)
                    .put(LNG, 20.523815);
            dest8
                    .put(TOKEN, "dest8")
                    .put(LAT, 54.722256)
                    .put(LNG, 20.522989);


            /*
            Россгартенские ворота 54.722051, 20.523815
            Памятник Василевскому 54.720838, 20.523863
            Казарма Кронпринц 54.717001, 20.531291
            Королевские ворота 54.713761, 20.535796
            Закхаймские ворота 54.709593, 20.538291
            Художественная галерея 54.708498, 20.5199
            Кафедральный собор 54.706383, 20.512047
            Музей янтаря 54.722256, 20.522989
             */

            JSONArray routesJsonArray = new JSONArray();
            routesJsonArray
                    .put(dest7)
                    .put(dest6)
                    .put(dest5)
                    .put(dest4)
                    .put(dest3)
                    .put(dest2)
                    .put(dest1)
                    .put(dest8);

            JSONObject routeJsonObject = new JSONObject();
            routeJsonObject.put(ROUTE, routesJsonArray);

            Log.d(TAG, "routeJsonObject=" + routeJsonObject.toString());

            getSharedPreferences(ROUTE, MODE_PRIVATE)
                    .edit()
                    .putString(ROUTE, routeJsonObject.toString())
                    .apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();

    }
}

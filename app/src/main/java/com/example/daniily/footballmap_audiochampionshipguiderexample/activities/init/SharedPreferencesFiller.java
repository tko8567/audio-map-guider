package com.example.daniily.footballmap_audiochampionshipguiderexample.activities.init;

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
            dest1
                    .put(TOKEN, "dest1")
                    .put(LAT, 54.706383)
                    .put(LNG, 20.512047);
            dest2
                    .put(TOKEN, "dest2")
                    .put(LAT, 54.706634)
                    .put(LNG, 20.500288);

            JSONArray routesJsonArray = new JSONArray();
            routesJsonArray
                    .put(dest1)
                    .put(dest2);

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

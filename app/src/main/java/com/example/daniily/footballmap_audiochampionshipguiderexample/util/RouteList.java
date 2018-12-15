package com.example.daniily.footballmap_audiochampionshipguiderexample.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents a list of routes - usually a root element of JSON with all the routes
 */
public class RouteList extends DataTemplate {


    public static final String EMPTY_LIST = "{\"_object\":\"RouteList\",\"name\":\"Route set\",\"routes\":[]}";

    public static final String ROUTES = "routes";
    public static final String PREFERENCES_NAME = "routes.json";

    protected final List<Route> routes;

    /**
     * Creates a RouteList with all routes from given params.
     * @param data JSONObject with all required data
     * @param context Context for fetching localized strings
     * @throws IllegalArgumentException if data was incomplete
     */
    public RouteList(JSONObject data, Context context) throws IllegalArgumentException {
        super(data, context);

        try {
            JSONArray jsonLandmarks = data.getJSONArray(ROUTES);
            List<Route> routes = new ArrayList<>();

            for (int i = 0; i < jsonLandmarks.length(); i++)
                routes.add(new Route(jsonLandmarks.getJSONObject(i), context));

            this.routes = Collections.unmodifiableList(routes);

        } catch (JSONException e) {
            throw new IllegalArgumentException("Cannot provide values to " + this.getClass().getSimpleName() + " from data=" + data.toString(), e);
        }
    }

    /**
     * Returns an immutable list if routes
     * @return List
     */
    public List<Route> getRoutes() {
        return routes;
    }
}

/*
Example:
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
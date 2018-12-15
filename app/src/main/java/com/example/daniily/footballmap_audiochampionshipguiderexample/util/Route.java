package com.example.daniily.footballmap_audiochampionshipguiderexample.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class for a route representation. Stores an information about landmarks and their order.
 *
 * @author tko8567
 * @see Landmark
 */
public class Route extends DataTemplate {

    public static final String ID = "id";
    public static final String LANDMARKS = "landmarks";
    protected final int id;
    protected final List<Landmark> route;


    public Route(JSONObject data, Context context) throws IllegalArgumentException {
        super(data, context);

        try {
            this.id = data.getInt(ID);

            JSONArray jsonLandmarks = data.getJSONArray(LANDMARKS);
            List<Landmark> landmarks = new ArrayList<>();

            for (int i = 0; i < jsonLandmarks.length(); i++)
                landmarks.add(new Landmark(jsonLandmarks.getJSONObject(i), context));

            this.route = Collections.unmodifiableList(landmarks);

        } catch (JSONException e) {
            throw new IllegalArgumentException("Cannot provide values to " + this.getClass().getSimpleName() + " from data=" + data.toString(), e);
        }
    }

    public int getId() {
        return id;
    }

    public List<Landmark> getRoute() {
        return route;
    }
}
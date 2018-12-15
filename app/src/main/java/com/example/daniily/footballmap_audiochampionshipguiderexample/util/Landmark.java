package com.example.daniily.footballmap_audiochampionshipguiderexample.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * Represents a landmark info - its lat/lng and a name.
 *
 * @author tko8567
 * @see com.example.daniily.footballmap_audiochampionshipguiderexample.util.DataTemplate
 */
public class Landmark extends DataTemplate {


    public static final String LAT = "lat";
    public static final String LNG = "lng";
    protected final BigDecimal lat;
    protected final BigDecimal lng;


    /**
     * Create a Landmark from JSONObject
     *
     * @param data    a JSONObject containing all needed data
     * @param context a Context to fetch the localization
     * @throws IllegalArgumentException if data does not contain the needed values
     */
    public Landmark(JSONObject data, Context context) throws IllegalArgumentException {
        super(data, context);

        try {
            this.lat = new BigDecimal(data.getDouble(LAT));
            this.lng = new BigDecimal(data.getDouble(LNG));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot provide values to " + this.getClass().getSimpleName() + " from data=" + data.toString(), e);
        }
    }

    /**
     * Returns a latitude of landmark position
     *
     * @return {@link BigDecimal} of the value
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * Returns a longitude of landmark position
     *
     * @return {@link BigDecimal} of the value
     */
    public BigDecimal getLng() {
        return lng;
    }
}
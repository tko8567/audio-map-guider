package com.example.daniily.footballmap_audiochampionshipguiderexample.util;

import android.content.Context;
import android.util.Log;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * DataTemplate class is the base class to create and store the data of route objects from
 * JSONObjects.  JSONObject should have its "_object" field which stores the type name and "name"
 * field to store the readable name or a reference for a localized string name of an object. In the
 * JSONObject a localized string is started with '@' and provided with a name of a reference. For
 * example, a value <code>@templateName_first</code> means that it needs a string
 * "templateName_first" to be accessible via <code>@string/templateName_first</code>. A translatable
 * field and its key are indicated with {@link Translatable} annotation.
 *
 * @author tko8567
 */
public class DataTemplate {

    public static final String OBJECT = "_object";
    @Translatable
    public static final String NAME = "name";
    private static final String TAG = "DataTemplate";
    protected final String object;
    @Translatable
    protected final String name;
    private final Context context;
    private final JSONObject jsonData;

    /**
     * Creates a stock data from given JSONObject and context. Context is needed for localization.
     *
     * @param data
     * @param context
     * @throws IllegalArgumentException
     */
    public DataTemplate(JSONObject data, Context context) throws IllegalArgumentException {
        this.context = context;
        this.jsonData = data;
        try {
            Log.d(TAG, "Creating DataTemplate with data=" + data);
            this.object = data.getString(OBJECT);
            this.name = getLocalizedString(data.getString(NAME));
        } catch (JSONException | NullPointerException e) {
            throw new IllegalArgumentException("Cannot provide values to " + this.getClass().getSimpleName() + " from data=" + data.toString(), e);
        }
    }

    /**
     * Try to fetch the localized string from context resources with id from R.string.
     * Returns the same string if not fetched
     *
     * @param name name of field in R.string to fetch the localization
     * @return localized string or the same one
     */
    protected String getLocalizedString(String name) {

        if (name.startsWith("@"))
            try {
                return context.getString(
                        (int) R.string.class.getDeclaredField(name.substring(1)).get(null)
                );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        return name;
    }

    /**
     * Get an object type of this object
     *
     * @return object type
     */
    public String getObject() {
        return object;
    }

    /**
     * Get a displayable name of this object
     *
     * @return name of this object
     */
    public String getName() {
        return name;
    }

    /**
     * Get the JSON this object was created from
     *
     * @return String representation of the original JSON
     */
    public String getJsonDataAsString() {
        return jsonData.toString();
    }

    /**
     * Indicates that this field should be a translatable one
     */
    @Target(ElementType.FIELD)
    @interface Translatable {
    }
}
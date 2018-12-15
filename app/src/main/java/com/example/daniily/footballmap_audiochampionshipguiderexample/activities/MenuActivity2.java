package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
import com.example.daniily.footballmap_audiochampionshipguiderexample.activities.view.RouteRecyclerViewAdapter;
import com.example.daniily.footballmap_audiochampionshipguiderexample.util.Route;
import com.example.daniily.footballmap_audiochampionshipguiderexample.util.RouteList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tko8567
 */
public class MenuActivity2 extends AppCompatActivity {


    private static final String TAG = "MenuActivity2";
    private RouteList routeList;
    private TextView menuHeader;

    private RecyclerView routesRecyclerView;
    private RouteRecyclerViewAdapter routesAdapter;
    private RecyclerView.LayoutManager routesLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        routesRecyclerView = findViewById(R.id.routesGroup);
        menuHeader = findViewById(R.id.menuHeader);

        JSONObject jsonRouteList = null;
        try {
            jsonRouteList =
                    new JSONObject(getSharedPreferences(RouteList.PREFERENCES_NAME, MODE_PRIVATE)
                            .getString(RouteList.ROUTES, RouteList.EMPTY_LIST));
            Log.d(TAG, "jsonRouteList=" + jsonRouteList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            finish();
        }
        try {
            routeList = new RouteList(jsonRouteList, this);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            routeList = null;
        }

        if (routeList == null || routeList.getRoutes().isEmpty()) {
            menuHeader.setText(R.string.noRoutesAvailable);
        } else {
            defineRoutesRecyclerView();
        }


    }

    /**
     * Set up a RecyclerView
     */
    private void defineRoutesRecyclerView() {

        List<String> routeNames = new LinkedList<>();

        for (Route route : routeList.getRoutes()) {
            routeNames.add(route.getName());
        }

        routesLayoutManager = new LinearLayoutManager(this);
        routesRecyclerView.setLayoutManager(routesLayoutManager);

        routesAdapter = new RouteRecyclerViewAdapter(routeNames.toArray(new String[0]));
        routesAdapter.setOnItemClickListener((v, position) -> {
            Intent mapsIntent = new Intent(this, MapsActivity2.class);
            mapsIntent.putExtra(
                    Route.class.getSimpleName(),
                    routeList.getRoutes().get(position).getJsonDataAsString()
            );
            startActivity(mapsIntent);
        });
        routesRecyclerView.setAdapter(routesAdapter);
    }
}

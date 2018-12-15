package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.CallbackOnResponse;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.NetworkModule;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models.Routes;
import com.example.daniily.footballmap_audiochampionshipguiderexample.util.Landmark;
import com.example.daniily.footballmap_audiochampionshipguiderexample.util.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * @author tko8567
 */
public class MapsActivity2 extends AppCompatActivity {

    private volatile Route mCurrentRoute;
    private GoogleMap map;

    private static final String TAG = "MapsActivity2";
    private CallbackOnResponse polylineCallback = new CallbackOnResponse() {

        @Override
        public void onResponseSuccess(Response response) {

            try {
                List<LatLng> latLngs = ((Routes) response.body()).getCoordinates();

                map.addPolyline(new PolylineOptions()
                        .addAll(latLngs)
                        .color(0x80FFA000)
                        .width(12f));

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                onResponseError("Dead response");
            }
        }

        @Override
        public void onResponseError(String error) {
            Log.e(TAG, error);
        }
    };
    private OnMapReadyCallback mapCallback = map -> {

        this.map = map;

        NetworkModule nm = NetworkModule.getInstance();
        for (int i = 0; i < mCurrentRoute.getRoute().size() - 1; i++) {
            nm.directionsService.getPolyline(
                    new LatLng(
                            mCurrentRoute.getRoute().get(i).getLat().doubleValue(),
                            mCurrentRoute.getRoute().get(i).getLng().doubleValue()
                    ),
                    new LatLng(
                            mCurrentRoute.getRoute().get(i + 1).getLat().doubleValue(),
                            mCurrentRoute.getRoute().get(i + 1).getLng().doubleValue()
                    ),
                    polylineCallback
            );
        }


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(
                        mCurrentRoute.getRoute().get(0).getLat().doubleValue(),
                        mCurrentRoute.getRoute().get(0).getLng().doubleValue()
                ), 16
        ));

        // FIXME: 12/15/18 Get rid of it after solving a Google API problem
        drawRoutePolylines();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        try {
            JSONObject jsonRoute = new JSONObject(
                    getIntent().getStringExtra(Route.class.getSimpleName())
            );
            mCurrentRoute = new Route(jsonRoute, this);
            Log.i(TAG, "route=" + jsonRoute);
            Log.i(TAG, "currentRoute=" + mCurrentRoute);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapCallback);
    }

    private void drawRoutePolylines() {
        ArrayList<LatLng> latLngs = new ArrayList<>();

        for (Landmark landmark: mCurrentRoute.getRoute()) {

            LatLng latLng = new LatLng(
                    landmark.getLat().doubleValue(),
                    landmark.getLng().doubleValue()
            );

            latLngs.add(latLng);

            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker())
                    .alpha(0.8f)
                    .title(landmark.getName())
                    .position(latLng)
            );
        }

        map.addPolyline(new PolylineOptions()
                .addAll(latLngs)
                .color(getResources().getColor(R.color.colorPrimaryDark))
                .width(12f)
        );
    }
}

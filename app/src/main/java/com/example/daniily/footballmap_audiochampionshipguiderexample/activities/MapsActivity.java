package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.CallbackOnResponse;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.NetworkModule;
import com.example.daniily.footballmap_audiochampionshipguiderexample.networking.models.Routes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    private Dialog mOverlayOptionsDialog;
    private RadioGroup mMapTypeDialogRadioGroup;

    private SharedPreferences mSharedPreferences;
    private static final String ROUTE = "route";
    private static final String TOKEN = "token";
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    private static final int REQUEST_PERMISSION_LOCATION = 0;
    private static final String[] LOCATION_PERMISSIONS =
            {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mOverlayOptionsDialog = new Dialog(MapsActivity.this);
        mOverlayOptionsDialog.setContentView(R.layout.dialog_switch_map_type);

        mSharedPreferences = getSharedPreferences(ROUTE, MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(getString(R.string.map_menu_switch_map_type)).setOnMenuItemClickListener(mapTypeClick);
        return super.onCreateOptionsMenu(menu);
    }

    private MenuItem.OnMenuItemClickListener mapTypeClick = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            mOverlayOptionsDialog.show();
            mMapTypeDialogRadioGroup = mOverlayOptionsDialog.findViewById(R.id.map_type_dialog_radioGroup);
            mMapTypeDialogRadioGroup.check(mMap.getMapType());
            return true;
        }
    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String jsonRouteString = mSharedPreferences.getString(ROUTE, null);
        if (jsonRouteString == null) {
            Log.wtf(TAG, "jsonRouteString is null");
            throw new RuntimeException();
        }
        JSONObject jsonRoute;
        try {
            jsonRoute = new JSONObject(jsonRouteString);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        JSONArray jsonLandmarksArray;
        try {
            jsonLandmarksArray = (JSONArray) (jsonRoute.get(ROUTE));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        List<LatLng> latLngs = new ArrayList<>();
        for (int i = 0; i < 23; i++)
            try {
                JSONObject jsonNextLandmark = jsonLandmarksArray.getJSONObject(i);
                latLngs.add(new LatLng(
                        jsonNextLandmark.getDouble(LAT),
                        jsonNextLandmark.getDouble(LNG)
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        CallbackOnResponse networkCallback = new CallbackOnResponse() {
            @Override
            public void onResponseSuccess(Response response) {
                Log.d(TAG, "Success, response=" + Arrays.toString(((Routes) response.body()).getCoordinates().toArray()));
                List<LatLng> latLngs = ((Routes) response.body()).getCoordinates();
                mMap.addMarker(new MarkerOptions()
                        .position(latLngs.get(0))
                        .alpha(0.75f)
                        .icon(BitmapDescriptorFactory.fromBitmap(
                                ((BitmapDrawable)getResources().getDrawable(R.mipmap.location_pointer)).getBitmap()
                        )));
                /*for (LatLng latLng : latLngs) {
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                    );
                }*/
                mMap.addPolyline(new PolylineOptions()
                        .addAll(latLngs)
                        .color(0x80FFA000)
                        .width(12f)
                );
            }

            @Override
            public void onResponseError(String error) {
                Log.w(TAG, "Error, error=" + error);
            }
        };

        for (int i = 0; i < latLngs.size() - 1; i++)
            NetworkModule
                    .getInstance()
                    .directionsService
                    .getPolyline(latLngs.get(i), latLngs.get(i + 1), networkCallback);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, LOCATION_PERMISSIONS, REQUEST_PERMISSION_LOCATION);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(54.714618, 20.529448), 16
        ));
    }


    public void onMapTypeChangedClick(View v) {

        if (v.getId() == R.id.map_type_accept) {
            mMap.setMapType(mMapTypeDialogRadioGroup.getCheckedRadioButtonId());
        }
        mOverlayOptionsDialog.hide();

    }
}

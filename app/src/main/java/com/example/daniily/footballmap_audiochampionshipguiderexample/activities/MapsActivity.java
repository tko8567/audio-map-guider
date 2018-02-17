package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Dialog mOverlayOptionsDialog;
    private RadioGroup mMapTypeDialogRadioGroup;

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

        // executed on start when map is ready
    }


    public void onMapTypeChangedClick(View v) {

        if (v.getId() == R.id.map_type_accept) {
            mMap.setMapType(mMapTypeDialogRadioGroup.getCheckedRadioButtonId());
        }
        mOverlayOptionsDialog.hide();

    }

}

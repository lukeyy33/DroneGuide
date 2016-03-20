package com.lukewaugh.droneguide;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity {

    double endLat, endLon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String end_lat_string = extras.getString("END_LAT");
        String end_lon_string = extras.getString("END_LON");

        if (!end_lat_string.equals("") && !end_lon_string.equals("")) {
            endLat = Double.parseDouble(end_lat_string);
            endLon = Double.parseDouble(end_lon_string);
        }
        setContentView(R.layout.activity_maps);

    }


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
    public void onStart() {
        super.onStart();
//        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    MY_PERMISSION_ACCESS_FINE_LOCATION);
//        }

    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
package com.example.foodmanagementsystem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class LatLonFromGMAP extends FragmentActivity implements OnMapReadyCallback ,GoogleMap.OnMapLongClickListener{

    private GoogleMap mMap;
    private Geocoder geocoder;
    private double lat=0,lon=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lat_lon_from_g_m_a_p);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        try {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            geocoder=new Geocoder(this);

            if (!getIntent().getStringExtra("lat").equals("")) {
                lat=Double.parseDouble(getIntent().getStringExtra("lat"));
                lon=Double.parseDouble(getIntent().getStringExtra("lon"));
                Toast.makeText(this, lat+" "+lon, Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMapLongClickListener(this);


        try {
            List<Address> list=geocoder.getFromLocationName("chennai",1);
            if(list.size()>0){
                lat=12.7322;
                lon=80.0014;
                LatLng latLng=new LatLng(lat,lon);
                if (lat!=0) {
                    latLng=new LatLng(lat,lon);
                }
                MarkerOptions markerOptions=new MarkerOptions()
                        .position(latLng)
                        .title(list.get(0).getLocality());
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Log.d("LatLong",latLng.latitude+" "+latLng.longitude);
        try {
            lat=latLng.latitude;
            lon=latLng.longitude;
            mMap.clear();
            List<Address> list;
            list = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            mMap.addMarker(new MarkerOptions().position(latLng).title(list.get(0).getAddressLine(0)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();

        *//*Toast.makeText(this, lat+" "+lon, Toast.LENGTH_SHORT).show();*//*
        Intent i1=new Intent(this,DonorDonateBrief.class);
        i1.putExtra("lat",String.valueOf(lat));
        i1.putExtra("lon",String.valueOf(lon));
        startActivity(i1);
//        finish();*/

        Intent i = new Intent();
        i.putExtra("lat",String.valueOf(lat));
        i.putExtra("lon",String.valueOf(lon));
        setResult(1, i);
        finish();

    }
}
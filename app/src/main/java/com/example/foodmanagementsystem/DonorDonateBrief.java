package com.example.foodmanagementsystem;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityDonorDonateBriefBinding;
import com.example.foodmanagementsystem.interfaces.DummyMap;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;
import com.example.foodmanagementsystem.model.User;

import java.util.List;


public class DonorDonateBrief extends AppCompatActivity {
    private static final int PERMISSION_ID =1 ;
    private ActivityDonorDonateBriefBinding activityDonorDonateBriefBinding;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private String foodName="",foodDated="",foodExpire="",foodQty="",foodtype="",userId="";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Donate");
        activityDonorDonateBriefBinding=ActivityDonorDonateBriefBinding.inflate(getLayoutInflater());
        setContentView(activityDonorDonateBriefBinding.getRoot());
        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        foodName = getIntent().getStringExtra("foodName");
        foodDated = getIntent().getStringExtra("foodDated");
        foodExpire = getIntent().getStringExtra("foodExpire");
        foodQty = getIntent().getStringExtra("foodQty");
        foodtype = getIntent().getStringExtra("foodtype");
        userId = getIntent().getStringExtra("userId");

        new GetInfo().execute(Integer.parseInt(userId));

        try {
            activityDonorDonateBriefBinding.donorlat.setText(getIntent().getStringExtra("lat"));
            activityDonorDonateBriefBinding.donorlong.setText(getIntent().getStringExtra("lon"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                DonorDonateBrief.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                DonorDonateBrief.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                DonorDonateBrief.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {

            //mtd1
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(longi);
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
//                Toast.makeText(this, latitude+" "+longitude, Toast.LENGTH_LONG).show();
                activityDonorDonateBriefBinding.donorlat.setText(latitude);
                activityDonorDonateBriefBinding.donorlong.setText(longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void submit(View view) {

        if((!activityDonorDonateBriefBinding.donorName.getText().toString().equals(""))
                && (!activityDonorDonateBriefBinding.donorPh.getText().toString().equals(""))
                && (!activityDonorDonateBriefBinding.donorAddress.getText().toString().equals(""))
                && (!activityDonorDonateBriefBinding.donorlat.getText().toString().equals(""))
                &&(!activityDonorDonateBriefBinding.donorlong.getText().toString().equals(""))
        ){

            CommonFoodAcceptor commonFoodAcceptor=new CommonFoodAcceptor(
                    foodtype,foodName,foodDated,foodExpire,foodQty,
                    activityDonorDonateBriefBinding.donorName.getText().toString(),
                    activityDonorDonateBriefBinding.donorPh.getText().toString(),
                    activityDonorDonateBriefBinding.donorAddress.getText().toString(),
                    activityDonorDonateBriefBinding.donorlat.getText().toString(),
                    activityDonorDonateBriefBinding.donorlong.getText().toString(),userId
            );

            new InsertFoodInfo().execute(commonFoodAcceptor);

        }
        else{
            Toast.makeText(this, "Enter all fields.", Toast.LENGTH_SHORT).show();
        }

    }

    public void getLatLong(View view) {


        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                OnGPS();
            } else {
                getLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mapView(View view) {
        String latval=activityDonorDonateBriefBinding.donorlat.getText().toString();
        String lonval=activityDonorDonateBriefBinding.donorlong.getText().toString();

        Intent i1=new Intent(DonorDonateBrief.this, LatLonFromGMAP.class);
        i1.putExtra("lat",latval);
        i1.putExtra("lon",lonval);
        startActivityForResult(i1,1);

    }

    public class GetInfo extends AsyncTask<Integer,Void, User> {

        @Override
        protected User doInBackground(Integer... uid) {

            user= UserDataBase.getInstance(getApplicationContext()).userDao().findById(uid[0]);

            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            activityDonorDonateBriefBinding.donorName.setText(user.getName());
            activityDonorDonateBriefBinding.donorPh.setText(user.getPhonenos());
            activityDonorDonateBriefBinding.donorAddress.setText(user.getAddress());

        }
    }

    public class InsertFoodInfo extends AsyncTask<CommonFoodAcceptor,Void, Void> {

        @Override
        protected Void doInBackground(CommonFoodAcceptor... commonFoodAcceptors) {

            UserDataBase.getInstance(getApplicationContext()).commonFoodAcceptorDao().insertFoodData(commonFoodAcceptors[0]);
            List<CommonFoodAcceptor> info=UserDataBase.getInstance(getApplicationContext()).commonFoodAcceptorDao().showAll();
            Log.d("AllData",info.toString());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(DonorDonateBrief.this, "Food added for donor", Toast.LENGTH_SHORT).show();

            Intent i1=new Intent(DonorDonateBrief.this,DonorHomeScreen.class);
            i1.putExtra("userId",userId);
            startActivity(i1);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            if(resultCode==1){
                activityDonorDonateBriefBinding.donorlat.setText(data.getStringExtra("lat"));
                activityDonorDonateBriefBinding.donorlong.setText(data.getStringExtra("lon"));
            }


    }
}


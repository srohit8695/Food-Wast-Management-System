package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityDonorHomeScreenBinding;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;
import com.example.foodmanagementsystem.model.DonorHistory;
import com.example.foodmanagementsystem.model.User;

public class DonorHomeScreen extends AppCompatActivity {

    private ActivityDonorHomeScreenBinding activityDonorHomeScreenBinding;
    private int userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donor_home_screen);

        try {
            activityDonorHomeScreenBinding=ActivityDonorHomeScreenBinding.inflate(getLayoutInflater());
            setContentView(activityDonorHomeScreenBinding.getRoot());
            userId=Integer.parseInt(getIntent().getStringExtra("userId"));

            new InsertRoomDatabase().execute(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }




    public class InsertRoomDatabase extends AsyncTask<Integer,Void,User> {
        @Override
        protected User doInBackground(Integer... uid) {

            user= UserDataBase.getInstance(getApplicationContext()).userDao().findById(uid[0]);

            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            activityDonorHomeScreenBinding.usrId.setText(user.getUsername());
            activityDonorHomeScreenBinding.usrName.setText(user.getName());
            activityDonorHomeScreenBinding.usrPh.setText(user.getPhonenos());
            activityDonorHomeScreenBinding.address.setText(user.getPassword());
            activityDonorHomeScreenBinding.usrtp.setText(user.getUserType());

        }
    }

    public void donate(View view) {
        Intent i1=new Intent(DonorHomeScreen.this,DonorDonate.class);
        i1.putExtra("userId",""+userId);
        startActivity(i1);
    }

    public void accepted(View view) {
        Intent i1=new Intent(DonorHomeScreen.this, OrderAcceptorInfo.class);
        startActivity(i1);
    }

    public void history(View view) {
        Intent i1=new Intent(DonorHomeScreen.this, DonorHistoryView.class);
        i1.putExtra("userId",""+userId);
        startActivity(i1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signout:
                try {
                    new DeleteLogin().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class DeleteLogin extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            UserDataBase.getInstance(getApplicationContext()).loginDataDao().deletelogin();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i2=new Intent(DonorHomeScreen.this,Login.class);
            startActivity(i2);
        }
    }


    public void onBackPressed() {
        try{
            AlertDialog.Builder close_dialog = new AlertDialog.Builder(this);
            close_dialog.setTitle("Exit Application");
            close_dialog.setMessage("Wish to exit application?");
            close_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.finishAffinity(DonorHomeScreen.this);
//                    finish();
                }
            });
            close_dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            close_dialog.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
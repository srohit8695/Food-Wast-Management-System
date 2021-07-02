package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityAcceptorHomeScreenBinding;
import com.example.foodmanagementsystem.model.User;

public class AcceptorHomeScreen extends AppCompatActivity {

    private ActivityAcceptorHomeScreenBinding activityAcceptorHomeScreenBinding;
    private int userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityAcceptorHomeScreenBinding=ActivityAcceptorHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityAcceptorHomeScreenBinding.getRoot());

        userId=Integer.parseInt(getIntent().getStringExtra("userId"));
        new InsertRoomDatabase().execute(userId);
    }

    public void orderAvailable(View view) {
        Intent i1=new Intent(AcceptorHomeScreen.this,OrdersAvailable.class);
        i1.putExtra("userId",userId+"");
        startActivity(i1);
    }

    public void ordersAccepted(View view) {
        Intent i1=new Intent(AcceptorHomeScreen.this,AcceptorAccepted.class);
        i1.putExtra("userId",userId+"");
        startActivity(i1);
    }

    public void history(View view) {
        Intent i1=new Intent(AcceptorHomeScreen.this,AccepterHistoryView.class);
        i1.putExtra("userId",userId+"");
        startActivity(i1);
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

            activityAcceptorHomeScreenBinding.usrId.setText(user.getUsername());
            activityAcceptorHomeScreenBinding.usrName.setText(user.getName());
            activityAcceptorHomeScreenBinding.usrPh.setText(user.getPhonenos());
            activityAcceptorHomeScreenBinding.address.setText(user.getPassword());
            activityAcceptorHomeScreenBinding.usrtp.setText(user.getUserType());

        }
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
            Intent i2=new Intent(AcceptorHomeScreen.this,Login.class);
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
                    ActivityCompat.finishAffinity(AcceptorHomeScreen.this);
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
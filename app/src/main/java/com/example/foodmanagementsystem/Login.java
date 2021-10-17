package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodmanagementsystem.database.LoginDatabase;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.model.LoginData;
import com.example.foodmanagementsystem.model.User;

import java.util.List;

public class Login extends AppCompatActivity {

    private TextView uid,pwd;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        uid=findViewById(R.id.uid);
        pwd=findViewById(R.id.pwd);

        new ChkLoggedIn().execute();

    }




    public void chekLogin(View view) {

        if ((!uid.getText().toString().trim().equals(""))&&(!pwd.getText().toString().trim().equals(""))) {
            InsertRoomDatabase insertRoomDatabase=new InsertRoomDatabase();
            insertRoomDatabase.execute(uid.getText().toString().trim());
        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
        }

    }


    public class InsertRoomDatabase extends AsyncTask<String,Void,String> {
        private String retData="",urType="";
        @Override
        protected String doInBackground(String... uid) {


            try {
                if(UserDataBase.getInstance(getApplicationContext()).userDao().chkName(uid[0])){
                    User user=UserDataBase.getInstance(getApplicationContext()).userDao().findByName(uid[0]);
                    if(user.getPassword().trim().equals(pwd.getText().toString().trim())){
                        LoginData loginData=new LoginData(uid[0],pwd.getText().toString().trim(),user.getUserType().trim());
                        UserDataBase.getInstance(getApplicationContext()).loginDataDao().insertUser(loginData);
                        userId=user.getUserid();
                        retData="success";
                        urType=user.getUserType().trim();

                    }else{
                        Toast.makeText(Login.this, "Check user password", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    retData="fail";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return retData;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                if(s.equals("success")){

                    Toast.makeText(Login.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                    if(urType.equals("Donor")){
                        //donor login
                        Intent i1=new Intent(Login.this,DonorHomeScreen.class);
                        i1.putExtra("userId",String.valueOf(userId));
                        startActivity(i1);

                    }
                    else{
                        //acceptor login
                        Intent i1=new Intent(Login.this,AcceptorHomeScreen.class);
                        i1.putExtra("userId",String.valueOf(userId));
                        startActivity(i1);

                    }
                }
                else if(s.equals("fail")){
                    Toast.makeText(Login.this, "Registration Not Done!!", Toast.LENGTH_SHORT).show();
                    Intent i1=new Intent(Login.this,Registration.class);
                    startActivity(i1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void registrationPage(View view) {
        Intent i1=new Intent(Login.this,Registration.class);
        startActivity(i1);
    }

    public class ChkLoggedIn extends AsyncTask<Void,Void,Boolean> {
        private String donorTp="";
        boolean retdata;

        @Override
        protected Boolean doInBackground(Void... voids) {


            try {
                List<LoginData> data=UserDataBase.getInstance(getApplicationContext()).loginDataDao().getLogin();
                if(data.size()>0){
                    User user=UserDataBase.getInstance(getApplicationContext()).userDao().findByName(data.get(0).getUsername());
                    donorTp=data.get(0).getUserType();
                    userId=user.getUserid();
                    retdata= true;
                }else{
                    retdata= false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return retdata;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean)
            {
                if (donorTp.trim().equals("Donor")) {
                    Intent i1=new Intent(Login.this,DonorHomeScreen.class);
                    i1.putExtra("userId",String.valueOf(userId));
                    startActivity(i1);
                } else {
                    Intent i1=new Intent(Login.this,AcceptorHomeScreen.class);
                    i1.putExtra("userId",String.valueOf(userId));
                    startActivity(i1);
                }
            }

        }
    }

}
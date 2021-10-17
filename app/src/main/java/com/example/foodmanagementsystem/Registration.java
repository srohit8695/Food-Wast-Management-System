package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodmanagementsystem.database.LoginDatabase;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.model.LoginData;
import com.example.foodmanagementsystem.model.User;

public class Registration extends AppCompatActivity {

    private RadioGroup radiogrp;
    private EditText uname,uphone,uaddress,uid,pwd;
    private String usertype="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle("Registration");
        uname = findViewById(R.id.uname);
        uphone = findViewById(R.id.uphone);
        uaddress = findViewById(R.id.uaddress);
        uid = findViewById(R.id.uid);
        pwd = findViewById(R.id.pwd);
        radiogrp=findViewById(R.id.radiogrp);

        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                usertype=radioButton.getText().toString();
            }
        });

    }

    public void register(View view) {

        if(
                (!uname.getText().toString().trim().equals(""))&&
                (!uphone.getText().toString().trim().equals(""))&&
                (!uaddress.getText().toString().trim().equals(""))&&
                (!uid.getText().toString().trim().equals(""))&&
                (!pwd.getText().toString().trim().equals(""))&&
                (!usertype.trim().equals(""))
        )
        {
           new CheckUser().execute(uid.getText().toString().trim());
        }
        else{
            Toast.makeText(this, "Enter and Select all the fields.", Toast.LENGTH_SHORT).show();
        }

    }



    public class InsertUserRoomDatabase extends AsyncTask<User,Void, Void> {

        @Override
        protected Void doInBackground(User... user) {

            UserDataBase.getInstance(getApplicationContext()).userDao().insertUser(user[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(Registration.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public class CheckUser extends AsyncTask<String,Void,Boolean> {
        private Boolean retData;

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean){
                User user=new User(uid.getText().toString().trim(),pwd.getText().toString().trim(),uphone.getText().toString().trim(),
                        usertype.trim(),uaddress.getText().toString().trim(),uname.getText().toString().trim());

                InsertUserRoomDatabase insertUserRoomDatabase=new InsertUserRoomDatabase();
                insertUserRoomDatabase.execute(user);
            }else{
                Toast.makeText(Registration.this, "User Id already exist", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(String... uid) {

                if(UserDataBase.getInstance(getApplicationContext()).userDao().chkName(uid[0])){
                    retData=false;
                    }else{
                    retData=true;
                    }

            return retData;
        }


    }


}
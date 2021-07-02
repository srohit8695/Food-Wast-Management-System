package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.foodmanagementsystem.adapters.AcceptorInfoToDonorAdapter;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityOrderAcceptorInfoBinding;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;

import java.util.List;

public class OrderAcceptorInfo extends AppCompatActivity {

    private ActivityOrderAcceptorInfoBinding activityOrderAcceptorInfoBinding;
    private List<AcceptorInfoToDonor> info;
    private AcceptorInfoToDonorAdapter acceptorInfoToDonorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_acceptor_info);

        activityOrderAcceptorInfoBinding = ActivityOrderAcceptorInfoBinding.inflate(getLayoutInflater());
        setContentView(activityOrderAcceptorInfoBinding.getRoot());

        new GetInfo().execute();

    }


    public class GetInfo extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... todos) {

            info=UserDataBase.getInstance(getApplicationContext()).acceptorInfoToDonorDao().showAll();
            Log.d("info",info.toString());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            acceptorInfoToDonorAdapter=new AcceptorInfoToDonorAdapter(info);
            activityOrderAcceptorInfoBinding.list.setAdapter(acceptorInfoToDonorAdapter);

        }
    }


}
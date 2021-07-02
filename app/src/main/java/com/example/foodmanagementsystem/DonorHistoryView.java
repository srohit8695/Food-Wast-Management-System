package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.foodmanagementsystem.adapters.AcceptorHistoryAdapter;
import com.example.foodmanagementsystem.adapters.DonorHistoryAdapter;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityDonorHistoryViewBinding;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.DonorHistory;

import java.util.List;

public class DonorHistoryView extends AppCompatActivity {
    private String userId;
    private ActivityDonorHistoryViewBinding activityDonorHistoryViewBinding;
    private DonorHistoryAdapter donorHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donor_history_view);
        activityDonorHistoryViewBinding=ActivityDonorHistoryViewBinding.inflate(getLayoutInflater());
        setContentView(activityDonorHistoryViewBinding.getRoot());

        userId=getIntent().getStringExtra("userId");
        new GetHistory().execute();
    }

    public class GetHistory extends AsyncTask<Void,Void, List<DonorHistory>> {
        @Override
        protected List<DonorHistory> doInBackground(Void... todos) {

            List<DonorHistory> info= UserDataBase.getInstance(getApplicationContext()).donorHistoryDao().findById(userId);

            Log.d("info",info.toString());

            return info;
        }

        @Override
        protected void onPostExecute(List<DonorHistory> commonFoodAcceptors) {
            super.onPostExecute(commonFoodAcceptors);

            if (commonFoodAcceptors.size()>0) {
                donorHistoryAdapter=new DonorHistoryAdapter(commonFoodAcceptors);
                activityDonorHistoryViewBinding.list.setAdapter(donorHistoryAdapter);
            } else {
                Toast.makeText(DonorHistoryView.this, "No History is available!!", Toast.LENGTH_SHORT).show();
            }


        }
    }


}
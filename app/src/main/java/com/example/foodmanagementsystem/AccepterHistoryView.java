package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodmanagementsystem.adapters.AcceptorHistoryAdapter;
import com.example.foodmanagementsystem.adapters.CommonFoodAcceptorAdapter;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityAcceprorAcceptedBriefViewBinding;
import com.example.foodmanagementsystem.databinding.ActivityAccepterHistoryViewBinding;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class AccepterHistoryView extends AppCompatActivity {
    private String userId;
    private ActivityAccepterHistoryViewBinding activityAccepterHistoryViewBinding;
    private AcceptorHistoryAdapter acceptorHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accepter_history_view);
        activityAccepterHistoryViewBinding=ActivityAccepterHistoryViewBinding.inflate(getLayoutInflater());
        setContentView(activityAccepterHistoryViewBinding.getRoot());

        userId=getIntent().getStringExtra("userId");
        new GetHistory().execute();

    }

    public class GetHistory extends AsyncTask<Void,Void, List<AcceptorHistory>> {
        @Override
        protected List<AcceptorHistory> doInBackground(Void... todos) {

            List<AcceptorHistory> info= UserDataBase.getInstance(getApplicationContext()).acceptorHistoryDao().findById(userId);

            return info;
        }

        @Override
        protected void onPostExecute(List<AcceptorHistory> commonFoodAcceptors) {
            super.onPostExecute(commonFoodAcceptors);

            if (commonFoodAcceptors.size()>0) {
                acceptorHistoryAdapter=new AcceptorHistoryAdapter(commonFoodAcceptors);
                activityAccepterHistoryViewBinding.list.setAdapter(acceptorHistoryAdapter);
            } else {
                Toast.makeText(AccepterHistoryView.this, "No History is available!!", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
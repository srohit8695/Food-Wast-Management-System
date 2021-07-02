package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodmanagementsystem.adapters.AceptorAcceptedAdapter;
import com.example.foodmanagementsystem.adapters.CommonFoodAcceptorAdapter;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityAcceptorAcceptedBinding;
import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class AcceptorAccepted extends AppCompatActivity {

    private ActivityAcceptorAcceptedBinding activityAcceptorAcceptedBinding;
    private String userId;
    private AceptorAcceptedAdapter aceptorAcceptedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAcceptorAcceptedBinding=ActivityAcceptorAcceptedBinding.inflate(getLayoutInflater());
        setContentView(activityAcceptorAcceptedBinding.getRoot());

        userId=getIntent().getStringExtra("userId");

        new GetOrders().execute(userId);
    }


    public class GetOrders extends AsyncTask<String,Void,List<AcceptorAcceptedFood>> {
        @Override
        protected List<AcceptorAcceptedFood> doInBackground(String... todos) {

            List<AcceptorAcceptedFood> info= UserDataBase.getInstance(getApplicationContext()).acceptorAcceptedFoodDao().findByacceptorId(todos[0]);

            return info;
        }

        @Override
        protected void onPostExecute(List<AcceptorAcceptedFood> commonFoodAcceptors) {
            super.onPostExecute(commonFoodAcceptors);

            if (commonFoodAcceptors.size()>0) {
                aceptorAcceptedAdapter=new AceptorAcceptedAdapter(commonFoodAcceptors,AcceptorAccepted.this,userId);
                activityAcceptorAcceptedBinding.list.setAdapter(aceptorAcceptedAdapter);
            } else {
                Toast.makeText(AcceptorAccepted.this, "No Food is available!!", Toast.LENGTH_SHORT).show();
            }


        }
    }


}
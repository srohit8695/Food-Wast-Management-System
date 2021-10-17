package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodmanagementsystem.adapters.CommonFoodAcceptorAdapter;
import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityOrdersAvailableBinding;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class OrdersAvailable extends AppCompatActivity {

    private ActivityOrdersAvailableBinding activityOrdersAvailableBinding;
    private CommonFoodAcceptorAdapter commonFoodAcceptorAdapter;
    private String userId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Orders");
//        setContentView(R.layout.activity_orders_available);
        activityOrdersAvailableBinding=ActivityOrdersAvailableBinding.inflate(getLayoutInflater());
        setContentView(activityOrdersAvailableBinding.getRoot());
        userId=getIntent().getStringExtra("userId");
        new GetOrders().execute();


    }

    public class GetOrders extends AsyncTask<Void,Void,List<CommonFoodAcceptor>> {
        @Override
        protected List<CommonFoodAcceptor> doInBackground(Void... todos) {

            List<CommonFoodAcceptor> info= UserDataBase.getInstance(getApplicationContext()).commonFoodAcceptorDao().showAll();

            return info;
        }

        @Override
        protected void onPostExecute(List<CommonFoodAcceptor> commonFoodAcceptors) {
            super.onPostExecute(commonFoodAcceptors);

            if (commonFoodAcceptors.size()>0) {
                commonFoodAcceptorAdapter=new CommonFoodAcceptorAdapter(commonFoodAcceptors,OrdersAvailable.this,userId);
                activityOrdersAvailableBinding.list.setAdapter(commonFoodAcceptorAdapter);
            } else {
                Toast.makeText(OrdersAvailable.this, "No Food is available!!", Toast.LENGTH_SHORT).show();
            }


        }
    }


}
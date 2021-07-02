package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityOrderBriefViewBinding;
import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;
import com.example.foodmanagementsystem.model.User;

import java.util.List;

public class OrderBriefView extends AppCompatActivity {
    private CommonFoodAcceptor commonFoodAcceptor;
    private ActivityOrderBriefViewBinding activityOrderBriefViewBinding;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_brief_view);

        activityOrderBriefViewBinding=ActivityOrderBriefViewBinding.inflate(getLayoutInflater());
        setContentView(activityOrderBriefViewBinding.getRoot());

        userId=getIntent().getStringExtra("userId");
        Bundle data = getIntent().getExtras();
        commonFoodAcceptor = (CommonFoodAcceptor) data.getParcelable("info");
        activityOrderBriefViewBinding.foodType.setText(commonFoodAcceptor.getFoodType());
        activityOrderBriefViewBinding.foodName.setText(commonFoodAcceptor.getFoodName());
        activityOrderBriefViewBinding.foodDated.setText(commonFoodAcceptor.getFoodDated());
        activityOrderBriefViewBinding.foodExpire.setText(commonFoodAcceptor.getFoodExpire());
        activityOrderBriefViewBinding.foodQty.setText(commonFoodAcceptor.getFoodQty());
        activityOrderBriefViewBinding.donorName.setText(commonFoodAcceptor.getDonorName());
        activityOrderBriefViewBinding.donorPh.setText(commonFoodAcceptor.getDonorPh());
        activityOrderBriefViewBinding.donorAddress.setText(commonFoodAcceptor.getDonorAdd());

        activityOrderBriefViewBinding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Accept().execute(commonFoodAcceptor);
            }
        });

        activityOrderBriefViewBinding.viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(OrderBriefView.this,MapsViewLocation.class);
                i1.putExtra("lat",commonFoodAcceptor.getLatitude());
                i1.putExtra("lon",commonFoodAcceptor.getLongitude());
                i1.putExtra("address",commonFoodAcceptor.getDonorAdd());
                startActivity(i1);

            }
        });

    }


    public class Accept extends AsyncTask<CommonFoodAcceptor,Void,Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent i1=new Intent(OrderBriefView.this,AcceptorHomeScreen.class);
            i1.putExtra("userId",userId);
            startActivity(i1);
        }

        @Override
        protected Void doInBackground(CommonFoodAcceptor... todos) {

            User user=UserDataBase.getInstance(getApplicationContext()).userDao().findById(Integer.parseInt(userId));
            AcceptorInfoToDonor acceptorInfoToDonor=new AcceptorInfoToDonor(
                    user.getName(),user.getPhonenos(),user.getAddress(),todos[0].getFoodName()
            );

            UserDataBase.getInstance(getApplicationContext()).acceptorInfoToDonorDao().insertData(acceptorInfoToDonor);
            List<AcceptorInfoToDonor> info= UserDataBase.getInstance(getApplicationContext()).acceptorInfoToDonorDao().showAll();
            Log.d("DonorInfo",info.toString());
            int acceptorId=info.get(info.size()-1).getRef_id();

            UserDataBase.getInstance(getApplicationContext()).commonFoodAcceptorDao().deleteById(todos[0].getRefId());

            AcceptorAcceptedFood acceptorAcceptedFood=new AcceptorAcceptedFood(todos[0].getFoodType(),todos[0].getFoodName(),todos[0].getFoodDated(),
                    todos[0].getFoodExpire(),todos[0].getFoodQty(),todos[0].getDonorName(),todos[0].getDonorPh(),todos[0].getDonorAdd(),
                    todos[0].getLatitude(),todos[0].getLongitude(),userId,todos[0].getUserId(),String.valueOf(acceptorId));

            UserDataBase.getInstance(getApplicationContext()).acceptorAcceptedFoodDao().insertFoodData(acceptorAcceptedFood);
            Log.d("FoodInfo",UserDataBase.getInstance(getApplicationContext()).acceptorAcceptedFoodDao().showAll().toString());

            return null;
        }
    }

}
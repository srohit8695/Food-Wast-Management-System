package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodmanagementsystem.database.UserDataBase;
import com.example.foodmanagementsystem.databinding.ActivityAcceprorAcceptedBriefViewBinding;
import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;
import com.example.foodmanagementsystem.model.DonorHistory;

import java.util.List;

public class AcceprorAcceptedBriefView extends AppCompatActivity {
    private AcceptorAcceptedFood acceptorAcceptedFood;
    private ActivityAcceprorAcceptedBriefViewBinding activityAcceprorAcceptedBriefViewBinding;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAcceprorAcceptedBriefViewBinding=ActivityAcceprorAcceptedBriefViewBinding.inflate(getLayoutInflater());
        setContentView(activityAcceprorAcceptedBriefViewBinding.getRoot());


        Bundle data = getIntent().getExtras();
        acceptorAcceptedFood= (AcceptorAcceptedFood) data.getParcelable("info");
        userId=getIntent().getStringExtra("userId");

        activityAcceprorAcceptedBriefViewBinding.foodType.setText(acceptorAcceptedFood.getFoodType());
        activityAcceprorAcceptedBriefViewBinding.foodName.setText(acceptorAcceptedFood.getFoodName());
        activityAcceprorAcceptedBriefViewBinding.foodDated.setText(acceptorAcceptedFood.getFoodDated());
        activityAcceprorAcceptedBriefViewBinding.foodExpire.setText(acceptorAcceptedFood.getFoodExpire());
        activityAcceprorAcceptedBriefViewBinding.foodQty.setText(acceptorAcceptedFood.getFoodQty());
        activityAcceprorAcceptedBriefViewBinding.donorName.setText(acceptorAcceptedFood.getDonorName());
        activityAcceprorAcceptedBriefViewBinding.donorPh.setText(acceptorAcceptedFood.getDonorPh());
        activityAcceprorAcceptedBriefViewBinding.donorAddress.setText(acceptorAcceptedFood.getDonorAdd());


    }

    public void completed(View view) {
        new CompletedInfo().execute();
    }

    public void viewMap(View view) {
        Intent i1=new Intent(AcceprorAcceptedBriefView.this,MapsViewLocation.class);
        i1.putExtra("lat",acceptorAcceptedFood.getLatitude());
        i1.putExtra("lon",acceptorAcceptedFood.getLongitude());
        i1.putExtra("address",acceptorAcceptedFood.getDonorAdd());
        startActivity(i1);
    }


    public void cancel(View view) {
        new DeleteInfo().execute();
    }


    public class DeleteInfo extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... todos) {

            try {
                UserDataBase.getInstance(getApplicationContext()).acceptorInfoToDonorDao().deleteById(Integer.parseInt(acceptorAcceptedFood.getAcceptorId()));//delete acceptor info in donor
                UserDataBase.getInstance(getApplicationContext()).acceptorAcceptedFoodDao().deleteById(acceptorAcceptedFood.getRefId());


                CommonFoodAcceptor commonFoodAcceptor=new CommonFoodAcceptor(
                        acceptorAcceptedFood.getFoodType(),acceptorAcceptedFood.getFoodName(),acceptorAcceptedFood.getFoodDated(),acceptorAcceptedFood.getFoodExpire(),acceptorAcceptedFood.getFoodQty(),
                        acceptorAcceptedFood.getDonorName(),
                        acceptorAcceptedFood.getDonorPh(),
                        acceptorAcceptedFood.getDonorAdd(),
                        acceptorAcceptedFood.getLatitude(),
                        acceptorAcceptedFood.getLongitude(),acceptorAcceptedFood.getDonorUserId()
                );

                UserDataBase.getInstance(getApplicationContext()).commonFoodAcceptorDao().insertFoodData(commonFoodAcceptor);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(AcceprorAcceptedBriefView.this, "Order is deleted", Toast.LENGTH_SHORT).show();

        }
    }


    public class CompletedInfo extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... todos) {

            try {
                UserDataBase.getInstance(getApplicationContext()).acceptorInfoToDonorDao().deleteById(Integer.parseInt(acceptorAcceptedFood.getAcceptorId()));//delete acceptor info in donor
                UserDataBase.getInstance(getApplicationContext()).acceptorAcceptedFoodDao().deleteById(acceptorAcceptedFood.getRefId());// delete information in acceptoraccepted food table

                AcceptorHistory acceptorHistory=new AcceptorHistory(acceptorAcceptedFood.getDonorName(),
                        acceptorAcceptedFood.getDonorPh(), acceptorAcceptedFood.getDonorAdd(),acceptorAcceptedFood.getFoodName(),
                        acceptorAcceptedFood.getFoodDated(),acceptorAcceptedFood.getFoodExpire(),acceptorAcceptedFood.getFoodQty(),userId);

                UserDataBase.getInstance(getApplicationContext()).acceptorHistoryDao().inserthistory(acceptorHistory);//inserting into acceptorhistory

                DonorHistory donorHistory=new DonorHistory(acceptorAcceptedFood.getFoodName(),acceptorAcceptedFood.getFoodQty(),
                        acceptorAcceptedFood.getFoodDated(),acceptorAcceptedFood.getFoodExpire(),userId,acceptorAcceptedFood.getDonorUserId());

                UserDataBase.getInstance(getApplicationContext()).donorHistoryDao().insertHistory(donorHistory);
                List<DonorHistory> show=UserDataBase.getInstance(getApplicationContext()).donorHistoryDao().showAll();

                Log.d("donorHis",show.toString());

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(AcceprorAcceptedBriefView.this, "Order is Completed", Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(AcceprorAcceptedBriefView.this,AcceptorHomeScreen.class);
            i1.putExtra("userId",userId);
            startActivity(i1);
            finish();

        }
    }



}
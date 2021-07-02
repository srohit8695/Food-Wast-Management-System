package com.example.foodmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodmanagementsystem.databinding.ActivityDonorDonateBinding;

public class DonorDonate extends AppCompatActivity {
    private ActivityDonorDonateBinding activityDonorDonateBinding;
    private String foodtype="";
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDonorDonateBinding=ActivityDonorDonateBinding.inflate(getLayoutInflater());
        setContentView(activityDonorDonateBinding.getRoot());

        userId=getIntent().getStringExtra("userId");

        activityDonorDonateBinding.foodTp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                foodtype=radioButton.getText().toString();
            }
        });

    }

    public void save(View view) {

        if((!activityDonorDonateBinding.foodName.getText().toString().equals(""))
                && (!activityDonorDonateBinding.foodDated.getText().toString().equals(""))
                && (!activityDonorDonateBinding.foodExpire.getText().toString().equals(""))
                && (!activityDonorDonateBinding.foodQty.getText().toString().equals(""))
                &&(!foodtype.trim().equals(""))
        ){

            Intent i1=new Intent(DonorDonate.this,DonorDonateBrief.class);
            i1.putExtra("foodName",activityDonorDonateBinding.foodName.getText().toString());
            i1.putExtra("foodDated",activityDonorDonateBinding.foodDated.getText().toString());
            i1.putExtra("foodExpire",activityDonorDonateBinding.foodExpire.getText().toString());
            i1.putExtra("foodQty",activityDonorDonateBinding.foodQty.getText().toString());
            i1.putExtra("foodtype",foodtype);
            i1.putExtra("userId",userId);
            startActivity(i1);

        }
        else{
            Toast.makeText(this, "Select and Enter all fields.", Toast.LENGTH_SHORT).show();
        }


    }
}
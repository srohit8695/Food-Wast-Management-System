package com.example.foodmanagementsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodmanagementsystem.OrderBriefView;
import com.example.foodmanagementsystem.databinding.FoodavailabletempletBinding;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class CommonFoodAcceptorAdapter extends  RecyclerView.Adapter<CommonFoodAcceptorAdapter.MyViewHolder>{

    private List<CommonFoodAcceptor> info;
    private Context context;
    private String userId;

    public CommonFoodAcceptorAdapter(List<CommonFoodAcceptor> info,Context context,String userId) {
        this.info = info;
        this.context=context;
        this.userId=userId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

            FoodavailabletempletBinding foodavailabletempletBinding;
            public MyViewHolder(FoodavailabletempletBinding foodavailabletempletBinding) {
            super(foodavailabletempletBinding.getRoot());
            this.foodavailabletempletBinding=foodavailabletempletBinding;

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        FoodavailabletempletBinding foodavailabletempletBinding=FoodavailabletempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(foodavailabletempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CommonFoodAcceptor data=info.get(position);
        holder.foodavailabletempletBinding.setFoodData(data);
        holder.foodavailabletempletBinding.executePendingBindings();
        holder.foodavailabletempletBinding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(context, OrderBriefView.class);
                i1.putExtra("info",data);
                i1.putExtra("userId",userId);
                context.startActivity(i1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return info.size();
    }




}

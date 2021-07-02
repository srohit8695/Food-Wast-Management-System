package com.example.foodmanagementsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagementsystem.AcceprorAcceptedBriefView;
import com.example.foodmanagementsystem.OrderBriefView;
import com.example.foodmanagementsystem.databinding.AcceptoracceptedtempletBinding;
import com.example.foodmanagementsystem.databinding.FoodavailabletempletBinding;
import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class AceptorAcceptedAdapter extends RecyclerView.Adapter<AceptorAcceptedAdapter.MyViewHolder>{
    List<AcceptorAcceptedFood> info;
    Context context;
    String userId;

    public AceptorAcceptedAdapter(List<AcceptorAcceptedFood> info, Context context,String userId) {
        this.info = info;
        this.context = context;
        this.userId=userId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AcceptoracceptedtempletBinding acceptoracceptedtempletBinding;
        public MyViewHolder(AcceptoracceptedtempletBinding acceptoracceptedtempletBinding) {
            super(acceptoracceptedtempletBinding.getRoot());
            this.acceptoracceptedtempletBinding=acceptoracceptedtempletBinding;

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        AcceptoracceptedtempletBinding acceptoracceptedtempletBinding=AcceptoracceptedtempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(acceptoracceptedtempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AcceptorAcceptedFood data=info.get(position);
        holder.acceptoracceptedtempletBinding.setFoodData(data);
        holder.acceptoracceptedtempletBinding.executePendingBindings();
        holder.acceptoracceptedtempletBinding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(context, AcceprorAcceptedBriefView.class);
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

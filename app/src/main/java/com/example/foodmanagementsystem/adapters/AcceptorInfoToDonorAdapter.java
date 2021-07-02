package com.example.foodmanagementsystem.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagementsystem.OrderBriefView;
import com.example.foodmanagementsystem.databinding.AcceptorinfotempletBinding;
import com.example.foodmanagementsystem.databinding.FoodavailabletempletBinding;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

public class AcceptorInfoToDonorAdapter extends RecyclerView.Adapter<AcceptorInfoToDonorAdapter.MyViewHolder>{
    private List<AcceptorInfoToDonor> info;

    public AcceptorInfoToDonorAdapter(List<AcceptorInfoToDonor> info) {
        this.info = info;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AcceptorinfotempletBinding acceptorinfotempletBinding;
        public MyViewHolder(AcceptorinfotempletBinding acceptorinfotempletBinding) {
            super(acceptorinfotempletBinding.getRoot());
            this.acceptorinfotempletBinding=acceptorinfotempletBinding;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        AcceptorinfotempletBinding acceptorinfotempletBinding=AcceptorinfotempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(acceptorinfotempletBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AcceptorInfoToDonor data=info.get(position);
        holder.acceptorinfotempletBinding.setFoodData(data);
        holder.acceptorinfotempletBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return info.size();
    }


}

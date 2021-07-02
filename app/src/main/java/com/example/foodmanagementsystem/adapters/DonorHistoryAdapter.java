package com.example.foodmanagementsystem.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagementsystem.databinding.AcceptorHistoryTempletBinding;
import com.example.foodmanagementsystem.databinding.DonorHistoryTempletBinding;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.DonorHistory;

import java.util.List;

public class DonorHistoryAdapter extends RecyclerView.Adapter<DonorHistoryAdapter.MyViewHolder> {
    private List<DonorHistory> info;

    public DonorHistoryAdapter(List<DonorHistory> info) {
        this.info = info;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        DonorHistoryTempletBinding donorHistoryTempletBinding;
        public MyViewHolder(DonorHistoryTempletBinding donorHistoryTempletBinding) {
            super(donorHistoryTempletBinding.getRoot());
            this.donorHistoryTempletBinding=donorHistoryTempletBinding;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        DonorHistoryTempletBinding donorHistoryTempletBinding=DonorHistoryTempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(donorHistoryTempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonorHistory donorHistory=info.get(position);
        holder.donorHistoryTempletBinding.setFoodHistort(donorHistory);
        holder.donorHistoryTempletBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return info.size();
    }



}

package com.example.foodmanagementsystem.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagementsystem.databinding.AcceptorHistoryTempletBinding;
import com.example.foodmanagementsystem.databinding.AcceptorinfotempletBinding;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;

import java.util.List;

public class AcceptorHistoryAdapter extends RecyclerView.Adapter<AcceptorHistoryAdapter.MyViewHolder>{
    private List<AcceptorHistory> info;

    public AcceptorHistoryAdapter(List<AcceptorHistory> info) {
        this.info = info;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        AcceptorHistoryTempletBinding acceptorHistoryTempletBinding;
        public MyViewHolder(AcceptorHistoryTempletBinding acceptorHistoryTempletBinding) {
            super(acceptorHistoryTempletBinding.getRoot());
            this.acceptorHistoryTempletBinding=acceptorHistoryTempletBinding;
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        AcceptorHistoryTempletBinding acceptorHistoryTempletBinding=AcceptorHistoryTempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(acceptorHistoryTempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AcceptorHistory acceptorHistory= info.get(position);
        holder.acceptorHistoryTempletBinding.setFoodHistort(acceptorHistory);
        holder.acceptorHistoryTempletBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return info.size();
    }


}

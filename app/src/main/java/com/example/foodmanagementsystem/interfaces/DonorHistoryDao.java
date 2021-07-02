package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.DonorHistory;

import java.util.List;

@Dao
public interface DonorHistoryDao {


    @Insert
    void insertHistory(DonorHistory donorHistory);

    @Query("SELECT * FROM donor_history WHERE donor_id=:id")
    List<DonorHistory> findById(String id);

    @Query("SELECT * FROM donor_history")
    List<DonorHistory> showAll();

}

package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.User;

import java.util.List;

@Dao
public interface AcceptorHistoryDao {

    @Insert
    void inserthistory(AcceptorHistory acceptorHistory);

    @Query("SELECT * FROM acceptor_history WHERE acceptor_id=:id")
    List<AcceptorHistory> findById(String id);

}

package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;

import java.util.List;

@Dao
public interface AcceptorAcceptedFoodDao {

    @Insert
    void insertFoodData(AcceptorAcceptedFood commonFoodAcceptor);

    @Query("DELETE FROM AcceptorAcceptedFood WHERE refId = :id")
    void deleteById(int id);

    @Query("SELECT * FROM AcceptorAcceptedFood")
    List<AcceptorAcceptedFood> showAll();

    @Query("SELECT * FROM AcceptorAcceptedFood WHERE userId=:id")
    List<AcceptorAcceptedFood> findByacceptorId(String id);

}

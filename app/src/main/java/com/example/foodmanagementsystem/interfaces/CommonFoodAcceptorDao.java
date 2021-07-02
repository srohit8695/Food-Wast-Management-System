package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.CommonFoodAcceptor;
import com.example.foodmanagementsystem.model.User;

import java.util.List;

@Dao
public interface CommonFoodAcceptorDao {

    @Insert
    void insertFoodData(CommonFoodAcceptor commonFoodAcceptor);

    @Query("DELETE FROM CommonFoodAcceptor WHERE refId = :id")
    void deleteById(int id);

    @Query("Select * from CommonFoodAcceptor")
    List<CommonFoodAcceptor> showAll();


}

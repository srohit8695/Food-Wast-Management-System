package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;

import java.util.List;

@Dao
public interface AcceptorInfoToDonorDao {

    @Insert
    void insertData(AcceptorInfoToDonor acceptorInfoToDonor);


    @Query("DELETE FROM AcceptorInfoToDonor WHERE ref_id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM AcceptorInfoToDonor")
    List<AcceptorInfoToDonor> showAll();

}

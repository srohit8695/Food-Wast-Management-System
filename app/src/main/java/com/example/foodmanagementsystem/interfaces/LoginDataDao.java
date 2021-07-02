package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.LoginData;
import com.example.foodmanagementsystem.model.User;

import java.util.List;

@Dao
public interface LoginDataDao {

    @Insert
    void insertUser(LoginData loginData);

    @Query("Select * from login_table")
    List<LoginData> getLogin();

    @Delete
    void deleteLogin(LoginData loginData);

    @Query("delete from login_table")
    void deletelogin();

}

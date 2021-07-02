package com.example.foodmanagementsystem.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodmanagementsystem.model.User;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("Select * from user_table where username like :uid")
    User findByName(String uid);

    @Query("Select exists(Select * from user_table where username like :uid)")
    Boolean chkName(String uid);

    @Query("Select * from user_table where userid like :uid")
    User findById(int uid);

}

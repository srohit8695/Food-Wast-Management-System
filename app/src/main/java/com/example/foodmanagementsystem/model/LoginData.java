package com.example.foodmanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login_table")
public class LoginData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "userType")
    private String userType;

    public LoginData(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

package com.example.foodmanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(name = "username")
    private String username;//userId
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "phonenos")
    private String phonenos;
    @ColumnInfo(name = "userType")
    private String userType;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "name")
    private String name;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenos='" + phonenos + '\'' +
                ", userType='" + userType + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public User(String username, String password, String phonenos, String userType, String address, String name) {
        this.username = username;
        this.password = password;
        this.phonenos = phonenos;
        this.userType = userType;
        this.address = address;
        this.name = name;
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

    public String getPhonenos() {
        return phonenos;
    }

    public void setPhonenos(String phonenos) {
        this.phonenos = phonenos;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}

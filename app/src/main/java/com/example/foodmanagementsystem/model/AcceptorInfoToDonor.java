package com.example.foodmanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AcceptorInfoToDonor")
public class AcceptorInfoToDonor {
    @PrimaryKey(autoGenerate =true)
    @ColumnInfo(name = "ref_id")
    private int ref_id;
    @ColumnInfo(name = "acceptorName")
    private String acceptorName;
    @ColumnInfo(name = "acceptorPh")
    private String acceptorPh;
    @ColumnInfo(name = "acceptorAdd")
    private String acceptorAdd;
    @ColumnInfo(name = "foodName")
    private String foodName;

    public AcceptorInfoToDonor(String acceptorName, String acceptorPh, String acceptorAdd, String foodName) {
        this.acceptorName = acceptorName;
        this.acceptorPh = acceptorPh;
        this.acceptorAdd = acceptorAdd;
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "AcceptorInfoToDonor{" +
                "ref_id=" + ref_id +
                ", acceptorName='" + acceptorName + '\'' +
                ", acceptorPh='" + acceptorPh + '\'' +
                ", acceptorAdd='" + acceptorAdd + '\'' +
                '}';
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public String getAcceptorName() {
        return acceptorName;
    }

    public void setAcceptorName(String acceptorName) {
        this.acceptorName = acceptorName;
    }

    public String getAcceptorPh() {
        return acceptorPh;
    }

    public void setAcceptorPh(String acceptorPh) {
        this.acceptorPh = acceptorPh;
    }

    public String getAcceptorAdd() {
        return acceptorAdd;
    }

    public void setAcceptorAdd(String acceptorAdd) {
        this.acceptorAdd = acceptorAdd;
    }
}

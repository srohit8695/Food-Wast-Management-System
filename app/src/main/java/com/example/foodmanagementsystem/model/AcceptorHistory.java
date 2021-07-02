package com.example.foodmanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "acceptor_history")
public class AcceptorHistory {

    @PrimaryKey(autoGenerate =true)
    @ColumnInfo(name = "ref_id")
    private int ref_id;
    @ColumnInfo(name = "donorName")
    private String donorName;
    @ColumnInfo(name = "donorPh")
    private String donorPh;
    @ColumnInfo(name = "donotAddres")
    private String donotAddres;
    @ColumnInfo(name = "foodName")
    private String foodName;
    @ColumnInfo(name = "foodDated")
    private String foodDated;
    @ColumnInfo(name = "foodExpired")
    private String foodExpired;
    @ColumnInfo(name = "qty")
    private String qty;
    @ColumnInfo(name = "acceptor_id")
    private String acceptor_id;

    public AcceptorHistory(String donorName, String donorPh, String donotAddres, String foodName, String foodDated, String foodExpired, String qty, String acceptor_id) {
        this.donorName = donorName;
        this.donorPh = donorPh;
        this.donotAddres = donotAddres;
        this.foodName = foodName;
        this.foodDated = foodDated;
        this.foodExpired = foodExpired;
        this.qty = qty;
        this.acceptor_id = acceptor_id;
    }

    public String getAcceptor_id() {
        return acceptor_id;
    }

    public void setAcceptor_id(String acceptor_id) {
        this.acceptor_id = acceptor_id;
    }

    @Override
    public String toString() {
        return "AcceptorHistory{" +
                "ref_id=" + ref_id +
                ", donorName='" + donorName + '\'' +
                ", donorPh='" + donorPh + '\'' +
                ", donotAddres='" + donotAddres + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodDated='" + foodDated + '\'' +
                ", foodExpired='" + foodExpired + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPh() {
        return donorPh;
    }

    public void setDonorPh(String donorPh) {
        this.donorPh = donorPh;
    }

    public String getDonotAddres() {
        return donotAddres;
    }

    public void setDonotAddres(String donotAddres) {
        this.donotAddres = donotAddres;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDated() {
        return foodDated;
    }

    public void setFoodDated(String foodDated) {
        this.foodDated = foodDated;
    }

    public String getFoodExpired() {
        return foodExpired;
    }

    public void setFoodExpired(String foodExpired) {
        this.foodExpired = foodExpired;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}

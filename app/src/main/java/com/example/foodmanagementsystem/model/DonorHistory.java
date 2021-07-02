package com.example.foodmanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "donor_history")
public class DonorHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "refid")
    private int refid;
    @ColumnInfo(name = "foodName")
    private String foodName;
    @ColumnInfo(name = "foodQty")
    private String foodQty;
    @ColumnInfo(name = "foodDated")
    private String foodDated;
    @ColumnInfo(name = "foodExpire")
    private String foodExpire;
    @ColumnInfo(name = "acceptorName")
    private String acceptorName;
    @ColumnInfo(name = "donor_id")
    private String donor_id;

    public DonorHistory(String foodName, String foodQty, String foodDated, String foodExpire, String acceptorName, String donor_id) {
        this.foodName = foodName;
        this.foodQty = foodQty;
        this.foodDated = foodDated;
        this.foodExpire = foodExpire;
        this.acceptorName = acceptorName;
        this.donor_id = donor_id;
    }



    public int getRefid() {
        return refid;
    }

    public void setRefid(int refid) {
        this.refid = refid;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(String foodQty) {
        this.foodQty = foodQty;
    }

    public String getFoodDated() {
        return foodDated;
    }

    public void setFoodDated(String foodDated) {
        this.foodDated = foodDated;
    }

    public String getFoodExpire() {
        return foodExpire;
    }

    public void setFoodExpire(String foodExpire) {
        this.foodExpire = foodExpire;
    }

    public String getAcceptorName() {
        return acceptorName;
    }

    public String getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(String donor_id) {
        this.donor_id = donor_id;
    }

    public void setAcceptorName(String acceptorName) {
        this.acceptorName = acceptorName;
    }

    @Override
    public String toString() {
        return "DonorHistory{" +
                "refid=" + refid +
                ", foodName='" + foodName + '\'' +
                ", foodQty='" + foodQty + '\'' +
                ", foodDated='" + foodDated + '\'' +
                ", foodExpire='" + foodExpire + '\'' +
                ", acceptorName='" + acceptorName + '\'' +
                ", donor_id='" + donor_id + '\'' +
                '}';
    }
}

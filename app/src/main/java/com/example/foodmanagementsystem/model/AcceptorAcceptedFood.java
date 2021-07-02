package com.example.foodmanagementsystem.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*
This table is similar to commonFoodAcceptor table
as the datas are moved from the commonFoodAcceptor
table to this table
*/


@Entity(tableName = "AcceptorAcceptedFood")
public class AcceptorAcceptedFood implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "refId")
    private int refId;
    @ColumnInfo(name = "foodType")
    private String foodType;
    @ColumnInfo(name = "foodName")
    private String foodName;
    @ColumnInfo(name = "foodDated")
    private String foodDated;
    @ColumnInfo(name = "foodExpire")
    private String foodExpire;
    @ColumnInfo(name = "foodQty")
    private String foodQty;
    @ColumnInfo(name = "donorName")
    private String donorName;
    @ColumnInfo(name = "donorPh")
    private String donorPh;
    @ColumnInfo(name = "donorAdd")
    private String donorAdd;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "userId")
    private String userId;
    @ColumnInfo(name = "donorUserId")
    private String donorUserId;
    @ColumnInfo(name = "acceptorId")
    private String acceptorId;// it is

    public AcceptorAcceptedFood(String foodType, String foodName, String foodDated, String foodExpire, String foodQty, String donorName, String donorPh, String donorAdd, String latitude, String longitude, String userId, String donorUserId, String acceptorId) {
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodDated = foodDated;
        this.foodExpire = foodExpire;
        this.foodQty = foodQty;
        this.donorName = donorName;
        this.donorPh = donorPh;
        this.donorAdd = donorAdd;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
        this.donorUserId = donorUserId;
        this.acceptorId = acceptorId;
    }

    public String getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(String acceptorId) {
        this.acceptorId = acceptorId;
    }

    public String getDonorUserId() {
        return donorUserId;
    }

    public void setDonorUserId(String donorUserId) {
        this.donorUserId = donorUserId;
    }

    @Override
    public String toString() {
        return "AcceptorAcceptedFood{" +
                "refId=" + refId +
                ", foodType='" + foodType + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodDated='" + foodDated + '\'' +
                ", foodExpire='" + foodExpire + '\'' +
                ", foodQty='" + foodQty + '\'' +
                ", donorName='" + donorName + '\'' +
                ", donorPh='" + donorPh + '\'' +
                ", donorAdd='" + donorAdd + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", userId='" + userId + '\'' +
                ", donorUserId='" + donorUserId + '\'' +
                ", acceptorId='" + acceptorId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
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

    public String getFoodExpire() {
        return foodExpire;
    }

    public void setFoodExpire(String foodExpire) {
        this.foodExpire = foodExpire;
    }

    public String getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(String foodQty) {
        this.foodQty = foodQty;
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

    public String getDonorAdd() {
        return donorAdd;
    }

    public void setDonorAdd(String donorAdd) {
        this.donorAdd = donorAdd;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    protected AcceptorAcceptedFood(Parcel in) {
        refId = in.readInt();
        foodType = in.readString();
        foodName = in.readString();
        foodDated = in.readString();
        foodExpire = in.readString();
        foodQty = in.readString();
        donorName = in.readString();
        donorPh = in.readString();
        donorAdd = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        userId = in.readString();
        donorUserId = in.readString();
        acceptorId = in.readString();
    }

    public static final Creator<AcceptorAcceptedFood> CREATOR = new Creator<AcceptorAcceptedFood>() {
        @Override
        public AcceptorAcceptedFood createFromParcel(Parcel in) {
            return new AcceptorAcceptedFood(in);
        }

        @Override
        public AcceptorAcceptedFood[] newArray(int size) {
            return new AcceptorAcceptedFood[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(refId);
        dest.writeString(foodType);
        dest.writeString(foodName);
        dest.writeString(foodDated);
        dest.writeString(foodExpire);
        dest.writeString(foodQty);
        dest.writeString(donorName);
        dest.writeString(donorPh);
        dest.writeString(donorAdd);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(userId);
        dest.writeString(donorUserId);
        dest.writeString(acceptorId);
    }
}

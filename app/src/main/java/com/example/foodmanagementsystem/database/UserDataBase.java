package com.example.foodmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodmanagementsystem.interfaces.AcceptorAcceptedFoodDao;
import com.example.foodmanagementsystem.interfaces.AcceptorHistoryDao;
import com.example.foodmanagementsystem.interfaces.AcceptorInfoToDonorDao;
import com.example.foodmanagementsystem.interfaces.CommonFoodAcceptorDao;
import com.example.foodmanagementsystem.interfaces.DonorHistoryDao;
import com.example.foodmanagementsystem.interfaces.LoginDataDao;
import com.example.foodmanagementsystem.interfaces.UserDao;
import com.example.foodmanagementsystem.model.AcceptorAcceptedFood;
import com.example.foodmanagementsystem.model.AcceptorHistory;
import com.example.foodmanagementsystem.model.AcceptorInfoToDonor;
import com.example.foodmanagementsystem.model.CommonFoodAcceptor;
import com.example.foodmanagementsystem.model.DonorHistory;
import com.example.foodmanagementsystem.model.LoginData;
import com.example.foodmanagementsystem.model.User;

@Database(entities = {User.class, LoginData.class, CommonFoodAcceptor.class, AcceptorAcceptedFood.class, AcceptorInfoToDonor.class, AcceptorHistory.class, DonorHistory.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    private static volatile UserDataBase INSTANCE;

    public abstract UserDao userDao();
    public abstract LoginDataDao loginDataDao();
    public abstract CommonFoodAcceptorDao commonFoodAcceptorDao();
    public abstract AcceptorAcceptedFoodDao acceptorAcceptedFoodDao();
    public abstract AcceptorInfoToDonorDao acceptorInfoToDonorDao();
    public abstract AcceptorHistoryDao acceptorHistoryDao();
    public abstract DonorHistoryDao donorHistoryDao();


    public static UserDataBase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (UserDataBase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDataBase.class, "Food_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}

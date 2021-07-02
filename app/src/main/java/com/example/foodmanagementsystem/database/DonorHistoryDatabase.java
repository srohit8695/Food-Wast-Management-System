package com.example.foodmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodmanagementsystem.model.DonorHistory;

@Database(entities = {DonorHistory.class},version = 2)
public abstract class DonorHistoryDatabase extends RoomDatabase {

    private static volatile DonorHistoryDatabase INSTANCE;

    static DonorHistoryDatabase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (DonorHistoryDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DonorHistoryDatabase.class, "Food_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}

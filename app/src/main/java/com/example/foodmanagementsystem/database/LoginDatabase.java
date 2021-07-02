package com.example.foodmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodmanagementsystem.interfaces.LoginDataDao;
import com.example.foodmanagementsystem.interfaces.UserDao;
import com.example.foodmanagementsystem.model.LoginData;
import com.example.foodmanagementsystem.model.User;

/*public class LoginDatabase {
}*/

@Database(entities = {LoginData.class}, version = 2)
public abstract class LoginDatabase extends RoomDatabase {

    private static volatile LoginDatabase INSTANCE;

//    public abstract LoginDataDao loginDataDao();

    public static LoginDatabase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (LoginDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LoginDatabase.class, "Food_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
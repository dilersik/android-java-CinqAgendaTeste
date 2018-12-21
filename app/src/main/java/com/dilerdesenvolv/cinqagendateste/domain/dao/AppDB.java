package com.dilerdesenvolv.cinqagendateste.domain.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import com.dilerdesenvolv.cinqagendateste.MyApplication;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB INSTANCE;

    public abstract UserDAO userDao();

    public static AppDB getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(MyApplication.getInstance().getApplicationContext(), AppDB.class, "cinq-agenda-teste")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

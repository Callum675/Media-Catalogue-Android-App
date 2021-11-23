package com.example.movieandsongcatalogue.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Detail.class}, version = 1)
public abstract class DetailDatabase extends RoomDatabase {

    public abstract DetailDAO detailDAO();

    private static DetailDatabase INSTANCE ;

    public static DetailDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (DetailDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DetailDatabase.class, "detail_database")
                            //wipes and rebuilds
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


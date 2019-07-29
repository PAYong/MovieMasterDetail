package com.example.android.itunesappandroidv2.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android.itunesappandroidv2.model.Movie;

@Database(entities = Movie.class, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private  static MovieDatabase instance;

    public abstract MovieDao movieDao();

    public static  synchronized MovieDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class,
                    "movie_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}

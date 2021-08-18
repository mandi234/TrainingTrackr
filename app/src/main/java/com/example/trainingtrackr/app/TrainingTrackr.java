package com.example.trainingtrackr.app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.trainingtrackr.database.TrackrRoomDatabase;

public class TrainingTrackr extends Application {

    private static Context context;

    public static TrackrRoomDatabase getDb() {
        return db;
    }

    private static TrackrRoomDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this, TrackrRoomDatabase.class, "my-db2").allowMainThreadQueries().build();
    }

    public static Context getAppContext() {
        return TrainingTrackr.context;
    }
}

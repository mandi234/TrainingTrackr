package com.example.trainingtrackr.app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainingtrackr.model.TrackrRoomDatabase;

public class TrainingTrackr extends Application {

    private static Context context;

    public static TrackrRoomDatabase getDb() {
        return db;
    }

    private static TrackrRoomDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this, TrackrRoomDatabase.class, "my-db").allowMainThreadQueries().build();
    }

    public static Context getAppContext() {
        return TrainingTrackr.context;
    }
}

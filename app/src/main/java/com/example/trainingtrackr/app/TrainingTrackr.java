package com.example.trainingtrackr.app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.trainingtrackr.database.TrackrRoomDatabase;
import com.example.trainingtrackr.utils.InputParser;

public class TrainingTrackr extends Application {

    private static Context context;
    private static TrackrRoomDatabase db;

    public static TrackrRoomDatabase getDb() {
        return db;
    }

    public static Context getAppContext() {
        return TrainingTrackr.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int t = InputParser.parseInt("         ");
        System.out.println(t);
        db = Room.databaseBuilder(this, TrackrRoomDatabase.class, "training-trackr-db2").allowMainThreadQueries().build();
    }
}

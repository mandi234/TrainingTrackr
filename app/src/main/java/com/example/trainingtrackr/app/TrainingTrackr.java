package com.example.trainingtrackr.app;

import android.app.Application;

import androidx.room.Room;

import com.example.trainingtrackr.database.TrackrRoomDatabase;
import com.example.trainingtrackr.utils.CrashHandler;
import com.example.trainingtrackr.utils.InputParser;


public class TrainingTrackr extends Application {

    private static TrackrRoomDatabase db;

    public static TrackrRoomDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int t = InputParser.parseInt("         ");
        CrashHandler.getInstance().init(this);
        db = Room.databaseBuilder(this, TrackrRoomDatabase.class, "training-trackr-db2")
                .allowMainThreadQueries()
                .addMigrations(TrackrRoomDatabase.Migration_2_3)
                .build();
    }
}

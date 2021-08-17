package com.example.trainingtrackr.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Training.class}, version = 1)
public abstract class TrackrRoomDatabase extends RoomDatabase {
    public abstract TrainingDao trainingDao();
}

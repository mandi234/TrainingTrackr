package com.example.trainingtrackr.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trainingtrackr.model.training.Training;
import com.example.trainingtrackr.model.training.dao.TrainingDao;

@Database(entities = {Training.class}, version = 2)
public abstract class TrackrRoomDatabase extends RoomDatabase {
    public abstract TrainingDao trainingDao();
}

package com.example.trainingtrackr.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrainingDao {

    @Insert
    void addTraining(Training training);

    @Query("Select * from training")
    LiveData<List<Training>> getTrainings();
}

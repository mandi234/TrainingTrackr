package com.example.trainingtrackr.model.training.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.training.Training;

import java.util.List;

@Dao
public interface TrainingDao {

    @Insert
    void addTraining(Training training);

    @Query("Select * from training order by id desc")
    LiveData<List<Training>> getTrainings();

    @Insert
    void addExercise(Exercise exercise);



}

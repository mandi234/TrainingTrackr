package com.example.trainingtrackr.model.training.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

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

    @Transaction
    @Query("select * from exercise where trainingId = :trainingId")
    LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId);

    @Update
    void updateTraining(Training training);

    @Update
    void updateExercise(Exercise exercise);

    @Update
    int updateExercises(List<Exercise> exercises);

    @Delete
    void deleteTraining(Training training);

    @Delete
    void deleteExercise(Exercise exercise);

}

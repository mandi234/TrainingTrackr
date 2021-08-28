package com.example.trainingtrackr.model.training.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.training.Training;

import java.util.List;

@Dao
public interface TrainingDao {

    @Insert
    long addTraining(Training training);


    @Query("Select * from training order by date desc")
    LiveData<List<Training>> getTrainings();

    @Insert
    void addExercise(Exercise exercise);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addExercisesList(List<Exercise> exercises);

    @Transaction
    @Query("select * from exercise where trainingId = :trainingId")
    LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId);

    @Update
    void updateTraining(Training training);

    @Update
    void updateExercise(Exercise exercise);

    @Update
    int updateExercises(List<Exercise> exercises);

    @Update
    int updateTrainings(List<Training> trainings);

    @Delete
    void deleteTraining(Training training);

    @Delete
    void deleteExercise(Exercise exercise);

}

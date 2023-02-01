package com.example.trainingtrackr.model.exercise;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("select distinct name from exercise")
    List<String> getKnownExercises();

    @Query("select * from exercise e" +
            " inner join training t on e.trainingId = t.id" +
            " where e.name = :name order by t.date desc" +
            " limit 1")
    Exercise getLastExerciseByName(String name);
}

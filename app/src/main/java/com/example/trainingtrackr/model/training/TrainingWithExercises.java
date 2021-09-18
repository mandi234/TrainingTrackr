package com.example.trainingtrackr.model.training;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.trainingtrackr.model.exercise.Exercise;

import java.util.List;

public class TrainingWithExercises {

    @Embedded
    public Training training;

    @Relation(parentColumn = "id", entityColumn = "trainingId", entity = Exercise.class)
    private List<Exercise> exerciseList;
}

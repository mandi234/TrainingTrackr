package com.example.trainingtrackr.model.exercise;

import java.util.List;

public class ExerciseRepository {

    private volatile static ExerciseRepository instance;
    private final ExerciseDao exerciseDao;

    private ExerciseRepository(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public synchronized static ExerciseRepository getInstance(ExerciseDao exerciseDao) {
        if (instance == null) instance = new ExerciseRepository(exerciseDao);

        return instance;

    }

    public List<String> getKnownExercises() {
        return exerciseDao.getKnownExercises();
    }

    public Exercise getLastExerciseByName(String name) {
        return exerciseDao.getLastExerciseByName(name);
    }
}

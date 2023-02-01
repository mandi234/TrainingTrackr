package com.example.trainingtrackr.utils;

import com.example.trainingtrackr.app.TrainingTrackr;
import com.example.trainingtrackr.model.exercise.ExerciseRepository;
import com.example.trainingtrackr.model.training.TrainingRepository;
import com.example.trainingtrackr.ui.AppViewModelFactory;

public class InjectorUtils {

    private static InjectorUtils instance = null;

    private InjectorUtils() {

    }

    public static AppViewModelFactory provideTrainingsViewModelFactory() {

        TrainingRepository trainingRepository = TrainingRepository.getInstance(TrainingTrackr.getDb().trainingDao());
        ExerciseRepository exerciseRepository = ExerciseRepository.getInstance(TrainingTrackr.getDb().exerciseDao());
        return new AppViewModelFactory(trainingRepository, exerciseRepository);

    }

    public InjectorUtils getInstance() {
        if (instance == null) instance = new InjectorUtils();
        return instance;
    }
}

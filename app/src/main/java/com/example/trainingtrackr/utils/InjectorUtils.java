package com.example.trainingtrackr.utils;

import com.example.trainingtrackr.app.TrainingTrackr;
import com.example.trainingtrackr.model.training.TrainingRepository;
import com.example.trainingtrackr.ui.AppViewModelFactory;

public class InjectorUtils {

    private static InjectorUtils instance = null;

    private InjectorUtils(){

    }

    public InjectorUtils getInstance() {
        if(instance == null) instance = new InjectorUtils();
        return instance;
    }

    public static AppViewModelFactory provideTrainingsViewModelFactory() {



        TrainingRepository trainingRepository = TrainingRepository.getInstance(TrainingTrackr.getDb().trainingDao());
        return new AppViewModelFactory(trainingRepository);

    }
}

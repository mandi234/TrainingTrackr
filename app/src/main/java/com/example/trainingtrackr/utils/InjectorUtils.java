package com.example.trainingtrackr.utils;

import com.example.trainingtrackr.app.TrainingTrackr;
import com.example.trainingtrackr.model.FakeDatabase;
import com.example.trainingtrackr.model.TrainingRepository;
import com.example.trainingtrackr.ui.trainings.TrainingsViewModel;
import com.example.trainingtrackr.ui.trainings.TrainingsViewModelFactory;

public class InjectorUtils {

    private static InjectorUtils instance = null;

    private InjectorUtils(){

    }

    public InjectorUtils getInstance() {
        if(instance == null) instance = new InjectorUtils();
        return instance;
    }

    public static TrainingsViewModelFactory provideTrainingsViewModelFactory() {



        TrainingRepository trainingRepository = TrainingRepository.getInstance(TrainingTrackr.getDb().trainingDao());
        return new TrainingsViewModelFactory(trainingRepository);

    }
}

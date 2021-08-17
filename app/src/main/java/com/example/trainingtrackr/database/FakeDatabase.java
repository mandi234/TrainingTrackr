package com.example.trainingtrackr.database;

import com.example.trainingtrackr.model.training.dao.FakeTrainingDao;
import com.example.trainingtrackr.model.training.dao.TrainingDao;

public class FakeDatabase {

    private static volatile FakeDatabase instance = null;

    private TrainingDao trainingDao = new FakeTrainingDao() ;

    private FakeDatabase() {

    }

    public static synchronized FakeDatabase getInstance() {
        if(instance == null) instance = new FakeDatabase();

        return instance;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }
}

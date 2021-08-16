package com.example.trainingtrackr.model;

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

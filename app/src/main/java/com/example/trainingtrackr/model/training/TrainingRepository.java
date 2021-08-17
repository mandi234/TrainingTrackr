package com.example.trainingtrackr.model.training;


import androidx.lifecycle.LiveData;

import com.example.trainingtrackr.model.training.dao.TrainingDao;

import java.util.List;

public class TrainingRepository {

    private TrainingDao trainingDao;
    private volatile static TrainingRepository instance;

    private TrainingRepository(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public synchronized static TrainingRepository getInstance(TrainingDao trainingDao) {
        if(instance == null) instance = new TrainingRepository(trainingDao);

        return instance;

    }

    public void addTraining(Training training) {
        trainingDao.addTraining(training);
    }

    public LiveData<List<Training>> getTrainings() {
       return trainingDao.getTrainings();
    }
}

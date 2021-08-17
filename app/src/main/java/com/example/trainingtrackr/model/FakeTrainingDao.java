package com.example.trainingtrackr.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class FakeTrainingDao implements TrainingDao {

    private List<Training> trainingList = new ArrayList<>();
    private MutableLiveData<List<Training>> trainings = new MutableLiveData<>();
    {
        trainings.setValue(trainingList);
    }

    @Override
    public void addTraining(Training training) {
        trainingList.add(0, training);
        trainings.setValue(trainingList);
    }

    @Override
    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }
}

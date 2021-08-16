package com.example.trainingtrackr.model;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface TrainingDao {

    void addTraining(Training training);
    LiveData<List<Training>> getTrainings();
}

package com.example.trainingtrackr.model.training.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.training.Training;

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
    public void addExercise(Exercise exercise) {

    }

    @Override
    public LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId) {
        return null;
    }

    @Override
    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }
}

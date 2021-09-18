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
    public long addTraining(Training training) {
        trainingList.add(0, training);
        trainings.setValue(trainingList);
        return 0;
    }

    @Override
    public void addExercise(Exercise exercise) {

    }

    @Override
    public void addExercisesList(List<Exercise> exercises) {

    }

    @Override
    public LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId) {
        return null;
    }

    @Override
    public void updateTraining(Training training) {

    }

    @Override
    public void updateExercise(Exercise exercise) {

    }

    @Override
    public int updateExercises(List<Exercise> exercises) {
        return 0;
    }

    @Override
    public int updateTrainings(List<Training> trainings) {
        return 0;
    }

    @Override
    public void deleteTraining(Training training) {

    }

    @Override
    public void deleteExercise(Exercise exercise) {

    }

    @Override
    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }
}

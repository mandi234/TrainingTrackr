package com.example.trainingtrackr.model.training;


import androidx.lifecycle.LiveData;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.training.dao.TrainingDao;

import java.util.List;

public class TrainingRepository {

    private volatile static TrainingRepository instance;
    private final TrainingDao trainingDao;

    private TrainingRepository(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public synchronized static TrainingRepository getInstance(TrainingDao trainingDao) {
        if (instance == null) instance = new TrainingRepository(trainingDao);

        return instance;

    }

    public long addTraining(Training training) {
        return trainingDao.addTraining(training);
    }

    public LiveData<List<Training>> getTrainings() {
        return trainingDao.getTrainings();
    }

    public void addExercise(Exercise exercise) {
        trainingDao.addExercise(exercise);
    }

    public LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId) {
        return trainingDao.getExercisesByTrainingId(trainingId);
    }

    public void updateTraining(Training training) {
        trainingDao.updateTraining(training);
    }

    public void updateExercise(Exercise exercise) {
        trainingDao.updateExercise(exercise);
    }

    public int updateExercises(List<Exercise> exercises) {
        return trainingDao.updateExercises(exercises);
    }

    public int updateTrainings(List<Training> trainings) {
        return trainingDao.updateTrainings(trainings);
    }

    public void deleteTraining(Training training) {
        trainingDao.deleteTraining(training);
    }

    public void deleteExercise(Exercise exercise) {
        trainingDao.deleteExercise(exercise);
    }
}

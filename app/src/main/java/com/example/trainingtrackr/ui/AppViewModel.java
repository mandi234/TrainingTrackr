package com.example.trainingtrackr.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.exercise.ExerciseRepository;
import com.example.trainingtrackr.model.training.Training;
import com.example.trainingtrackr.model.training.TrainingRepository;

import java.util.List;
import java.util.Set;

public class AppViewModel extends ViewModel {

    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;

    public AppViewModel(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public long addTraining(Training training) {
        return trainingRepository.addTraining(training);
    }

    public void addExercise(Exercise exercise) {
        trainingRepository.addExercise(exercise);
    }

    public LiveData<List<Training>> getTrainings() {
        return trainingRepository.getTrainings();
    }

    public LiveData<List<Exercise>> getExercisesByTrainingId(long trainingId) {
        return trainingRepository.getExercisesByTrainingId(trainingId);
    }

    public void updateTraining(Training training) {
        trainingRepository.updateTraining(training);
    }

    public void updateExercise(Exercise exercise) {
        trainingRepository.updateExercise(exercise);
    }

    public int updateExercises(List<Exercise> exercises) {
        return trainingRepository.updateExercises(exercises);
    }

    public int updateTrainings(List<Training> trainings) {
        return trainingRepository.updateTrainings(trainings);
    }

    public void deleteTraining(Training training) {
        trainingRepository.deleteTraining(training);
    }

    public void deleteExercise(Exercise exercise) {
        trainingRepository.deleteExercise(exercise);
    }

    public List<String> getKnownExercises() {
        return exerciseRepository.getKnownExercises();
    }
}

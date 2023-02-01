package com.example.trainingtrackr.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.exercise.ExerciseRepository;
import com.example.trainingtrackr.model.training.TrainingRepository;

import org.jetbrains.annotations.NotNull;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;

    public AppViewModelFactory(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
    }


    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return modelClass.cast(new AppViewModel(trainingRepository, exerciseRepository));
    }
}

package com.example.trainingtrackr.ui.trainings;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trainingtrackr.model.training.TrainingRepository;

import org.jetbrains.annotations.NotNull;

public class TrainingsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private TrainingRepository trainingRepository;

    public TrainingsViewModelFactory(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return modelClass.cast(new TrainingsViewModel(trainingRepository));
    }
}

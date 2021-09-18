package com.example.trainingtrackr.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trainingtrackr.model.training.TrainingRepository;
import com.example.trainingtrackr.ui.AppViewModel;

import org.jetbrains.annotations.NotNull;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private TrainingRepository trainingRepository;

    public AppViewModelFactory(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return modelClass.cast(new AppViewModel(trainingRepository));
    }
}

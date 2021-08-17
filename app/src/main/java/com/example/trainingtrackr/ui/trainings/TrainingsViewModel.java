package com.example.trainingtrackr.ui.trainings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trainingtrackr.model.training.Training;
import com.example.trainingtrackr.model.training.TrainingRepository;

import java.util.List;

public class TrainingsViewModel extends ViewModel {

    private TrainingRepository trainingRepository;

    public TrainingsViewModel(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

   public void addTraining(Training training) {
        trainingRepository.addTraining(training);
   }

   public LiveData<List<Training>> getTrainings() {
        return trainingRepository.getTrainings();
   }

}

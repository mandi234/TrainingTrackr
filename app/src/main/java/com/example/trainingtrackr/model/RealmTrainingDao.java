package com.example.trainingtrackr.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trainingtrackr.app.TrainingTrackr;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmTrainingDao implements TrainingDao {


    private Realm realmInstance;
    private List<Training> trainingList = new ArrayList<>();
    private MutableLiveData<List<Training>> trainings = new MutableLiveData<>();
    {
        trainings.setValue(trainingList);
    }

    public RealmTrainingDao() {
        realmInstance = Realm.getInstance(TrainingTrackr.REALM_CONFIG);
    }

    @Override
    public void addTraining(Training training) {
        realmInstance.executeTransaction(transactionRealm -> {
            transactionRealm.insert(training);
        });
    }

    @Override
    public LiveData<List<Training>> getTrainings() {
        return null;
    }
}

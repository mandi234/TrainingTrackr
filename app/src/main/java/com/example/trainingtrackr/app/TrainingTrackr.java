package com.example.trainingtrackr.app;

import android.app.Application;

import com.example.trainingtrackr.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TrainingTrackr extends Application {

    public static final String REALM_NAME = "REALMDATABASE";
    public static final RealmConfiguration REALM_CONFIG = new RealmConfiguration.Builder().name(REALM_NAME).build();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}

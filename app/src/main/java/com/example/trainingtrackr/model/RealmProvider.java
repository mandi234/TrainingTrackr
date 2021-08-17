package com.example.trainingtrackr.model;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmProvider  {

    private static RealmProvider instance;

    private RealmProvider() {


    }

    public static synchronized RealmProvider getInstance() {
        if(instance == null) instance = new RealmProvider();

        return instance;
    }

    public Realm provideRealm(RealmConfiguration config) {
        return Realm.getInstance(config);
    }
}

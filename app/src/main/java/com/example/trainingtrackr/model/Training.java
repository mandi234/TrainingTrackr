package com.example.trainingtrackr.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Training {

    private Date date;
    private String name;

    public Training() {
    }

    public Training(String name) {
        this.name = name;
    }

    public Training(Date date, String name) {
        this(name);
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.trainingtrackr.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class Training {

    @ColumnInfo(name ="date")
    private String date;
    @ColumnInfo(name ="name")
    @PrimaryKey
    @NonNull
    private String name;

    public Training() {
    }

    public Training(String name) {
        this.name = name;
    }

    public Training(String date, String name) {
        this(name);
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

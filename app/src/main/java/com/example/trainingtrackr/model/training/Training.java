package com.example.trainingtrackr.model.training;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.trainingtrackr.model.exercise.Exercise;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Training {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name ="date")
    private String date;

    @ColumnInfo(name ="name")
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



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

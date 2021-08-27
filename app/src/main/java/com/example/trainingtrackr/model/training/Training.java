package com.example.trainingtrackr.model.training;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.trainingtrackr.model.exercise.Exercise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Training() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Training(String name) {
        this();
        this.name = name;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

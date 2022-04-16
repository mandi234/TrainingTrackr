package com.example.trainingtrackr.model.training;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Training {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "name")
    private String name;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Training() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
        this.timestamp = now.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Training(String name) {
        this();
        this.name = name;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

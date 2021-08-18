package com.example.trainingtrackr.model.exercise;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private long id;


    private String name;

    private long trainingId;

    private int sets;
    private int reps;
    private int weight;
    private String note;
    public Exercise() {
        this.name = "";
        this.sets = 0;
        this.reps = 0;
        this.weight = 0;
        this.note = "";
    }

    public Exercise(String name, int sets, int reps, int weight, String note) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

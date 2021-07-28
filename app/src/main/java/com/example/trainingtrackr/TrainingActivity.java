package com.example.trainingtrackr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.trainingtrackr.adapters.TrainingAdapter;
import com.example.trainingtrackr.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {

    private List<Exercise> exercisesList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        recyclerView = findViewById(R.id.training_recycler_view);
        exercisesList = new ArrayList<>();

        exercisesList.add(new Exercise("Deadlift", 5, 5, 50, "Test notki"));
        exercisesList.add(new Exercise("Squat", 2, 8, 150, "Test notki2"));
        exercisesList.add();
        initAdapter();


    }

    private void initAdapter() {
        TrainingAdapter trainingAdapter = new TrainingAdapter(exercisesList);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trainingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
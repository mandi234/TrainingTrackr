package com.example.trainingtrackr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
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

        exercisesList.add(new Exercise("Agata jest", 5, 5, 50, "Test notki"));
        exercisesList.add(new Exercise("SUPER!", 2, 8, 150, "Test notki2"));

        initAdapter();


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter() {
        TrainingAdapter trainingAdapter = new TrainingAdapter(exercisesList);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trainingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}
package com.example.trainingtrackr.ui.exercies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.adapters.ExercisesAdapter;
import com.example.trainingtrackr.model.exercise.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {

    private List<Exercise> exercisesList;
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExercisesAdapter trainingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.training_recycler_view);
        addExerciseFab = findViewById(R.id.add_exercise_fab);
        exercisesList = new ArrayList<>();

        System.out.println(getIntent().getLongExtra("trainingId", 0));


        initAdapter();

        addExerciseFab.setOnClickListener(v -> {
            exercisesList.add(new Exercise());
            trainingAdapter.notifyItemInserted(exercisesList.size()-1);
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter() {
        trainingAdapter = new ExercisesAdapter(exercisesList);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trainingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}
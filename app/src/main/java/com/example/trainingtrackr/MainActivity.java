package com.example.trainingtrackr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trainingtrackr.adapters.MainAdapter;
import com.example.trainingtrackr.adapters.TrainingAdapter;
import com.example.trainingtrackr.model.Exercise;
import com.example.trainingtrackr.model.Training;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton fab;
    private List<Training> trainingsList;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recycler_view);
        fab = findViewById(R.id.floatingActionButton);
        trainingsList = new ArrayList<>();

        initAdapter();
        fab.setOnClickListener(v -> {
            trainingsList.add(new Training());
            mainAdapter.notifyItemInserted(trainingsList.size()-1);
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter() {
        mainAdapter = new MainAdapter(trainingsList);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}
package com.example.trainingtrackr.ui.trainings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.adapters.TrainingsAdapter;
import com.example.trainingtrackr.model.Training;
import com.example.trainingtrackr.utils.InjectorUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrainingsActivity extends AppCompatActivity {

    public FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TrainingsAdapter trainingsAdapter;
    private static int fabClicks = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);
        recyclerView = findViewById(R.id.main_recycler_view);
        fab = findViewById(R.id.floatingActionButton);
        initUI();
    }

    private void initUI() {
        TrainingsViewModelFactory factory = InjectorUtils.provideTrainingsViewModelFactory();
        TrainingsViewModel trainingsViewModel = new ViewModelProvider(this, factory).get(TrainingsViewModel.class);


        trainingsViewModel.getTrainings().observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(List<Training> trainings) {
                initAdapter(TrainingsActivity.this, trainings);
                System.out.println(trainings.toString());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClicks++;
                trainingsViewModel.addTraining(new Training(""+fabClicks));

            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter(Activity context, List<Training> trainings) {
        TrainingsAdapter trainingsAdapter = new TrainingsAdapter(context, trainings);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trainingsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        //dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        //recyclerView.addItemDecoration(dividerItemDecoration);

    }


}
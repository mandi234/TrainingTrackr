package com.example.trainingtrackr.ui.exercies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.adapters.ExercisesAdapter;
import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.ui.AppViewModel;
import com.example.trainingtrackr.ui.AppViewModelFactory;
import com.example.trainingtrackr.utils.InjectorUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExercisesActivity extends AppCompatActivity implements ExercisesAdapter.OnExerciseListener {

    private List<Exercise> exercisesList;
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExercisesAdapter exercisesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.training_recycler_view);
        addExerciseFab = findViewById(R.id.add_exercise_fab);
        exercisesList = new ArrayList<>();

        long trainingId = getIntent().getLongExtra("trainingId", 0);
        AppViewModelFactory factory = InjectorUtils.provideTrainingsViewModelFactory();
        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);

        appViewModel.getExercisesByTrainingId(trainingId).observe(this, new Observer<List<Exercise>>() {

            @Override
            public void onChanged(List<Exercise> exercises) {
                exercisesList = exercises;
                initAdapter(ExercisesActivity.this, exercises);
            }
        });


        addExerciseFab.setOnClickListener(v -> {
            Exercise exercise = new Exercise(trainingId);

            appViewModel.addExercise(exercise);

        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter(Activity context, List<Exercise> exercises) {
        exercisesAdapter = new ExercisesAdapter(exercises, this::onExerciseLongClick);
        layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onExerciseLongClick(int position) {
        appViewModel.deleteExercise(exercisesList.get(position));
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStop() {
        if( !exercisesList.isEmpty())
            updateExercises();
        super.onStop();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateExercises() {
        for(int i = 0; i < exercisesList.size(); i++) {
            ExercisesAdapter.ExcerciseViewHolder holder = (ExercisesAdapter.ExcerciseViewHolder) recyclerView.findViewHolderForLayoutPosition(0);
            exercisesList.get(i).setReps(Integer.parseInt(holder.getRepsEditText().getText().toString()));
            exercisesList.get(i).setSets(Integer.parseInt(holder.getSetsEditText().getText().toString()));
            exercisesList.get(i).setWeight(Integer.parseInt(holder.getWeightEditText().getText().toString()));
        }
        appViewModel.updateExercises(exercisesList);
    }


}
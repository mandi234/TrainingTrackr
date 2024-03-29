package com.example.trainingtrackr.ui.exercies;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.adapters.ExercisesAdapter;
import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.ui.AppViewModel;
import com.example.trainingtrackr.ui.AppViewModelFactory;
import com.example.trainingtrackr.utils.InjectorUtils;
import com.example.trainingtrackr.utils.InputParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity implements ExercisesAdapter.OnExerciseListener {

    private List<Exercise> exercisesList;
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExercisesAdapter exercisesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppViewModel appViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        appViewModel.getExercisesByTrainingId(trainingId).observe(this, exercises -> {
            exercisesList = exercises;
            initAdapter(ExercisesActivity.this, exercises);
        });


        addExerciseFab.setOnClickListener(v -> {
            updateExercises();
            Exercise exercise = new Exercise(trainingId);

            appViewModel.addExercise(exercise);

        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter(Activity context, List<Exercise> exercises) {
        exercisesAdapter = new ExercisesAdapter(exercises, this, appViewModel.getKnownExercises());
        layoutManager = new LinearLayoutManager(context);
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
        if (!exercisesList.isEmpty())
            updateExercises();
        super.onStop();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateExercises() {
        for (int i = 0; i < exercisesList.size(); i++) {

            ExercisesAdapter.ExcerciseViewHolder holder = (ExercisesAdapter.ExcerciseViewHolder) recyclerView.findViewHolderForLayoutPosition(i);
            if ( holder != null ) {
                exercisesList.get(i).setReps(InputParser.parseInt(holder.getRepsEditText().getText().toString()));
                exercisesList.get(i).setSets(InputParser.parseInt(holder.getSetsEditText().getText().toString()));
                exercisesList.get(i).setWeight(InputParser.parseDouble(holder.getWeightEditText().getText().toString()));
                exercisesList.get(i).setName(holder.getExerciseNameAutoCompTextView().getText().toString());
                exercisesList.get(i).setNote(holder.getNotesEditText().getText().toString());
            }
        }
        appViewModel.updateExercises(exercisesList);
    }


}
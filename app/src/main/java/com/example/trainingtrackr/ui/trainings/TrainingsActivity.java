package com.example.trainingtrackr.ui.trainings;

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
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.adapters.TrainingsAdapter;
import com.example.trainingtrackr.model.training.Training;
import com.example.trainingtrackr.ui.AppViewModel;
import com.example.trainingtrackr.ui.AppViewModelFactory;
import com.example.trainingtrackr.ui.exercies.ExercisesActivity;
import com.example.trainingtrackr.utils.InjectorUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.List;

public class TrainingsActivity extends AppCompatActivity implements TrainingsAdapter.OnTrainingListener {

    public FloatingActionButton fab;
    private RecyclerView recyclerView;

    private static int fabClicks = 0;
    private List<Training> trainingsList;
    private AppViewModel appViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);
        recyclerView = findViewById(R.id.main_recycler_view);
        fab = findViewById(R.id.floatingActionButton);
        Calendar newCalendar = Calendar.getInstance();

        initUI();
    }

    private void initUI() {
        AppViewModelFactory factory = InjectorUtils.provideTrainingsViewModelFactory();
        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);


        appViewModel.getTrainings().observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(List<Training> trainings) {
                trainingsList = trainings;
                initAdapter(TrainingsActivity.this, trainings);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                appViewModel.addTraining(new Training());

            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.exercise_item_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initAdapter(Activity context, List<Training> trainings) {
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        TrainingsAdapter trainingsAdapter = new TrainingsAdapter(context, trainings, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trainingsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }


    @Override
    public void onTrainingClick(int position) {
        Intent intent = new Intent(TrainingsActivity.this, ExercisesActivity.class);
        intent.putExtra("trainingId", trainingsList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onTrainingLongClick(int position) {
        appViewModel.deleteTraining(trainingsList.get(position));
        return true;
    }

    @Override
    public void onDateClick(int position) {
        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog  StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Training training = trainingsList.get(position);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime ldt = LocalDateTime.ofInstant(newDate.toInstant(), newDate.getTimeZone().toZoneId());
                training.setDate(dtf.format(ldt));
                appViewModel.updateTraining(training);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        StartTime.show();
    }

    @Override
    protected void onStop() {
        appViewModel.updateTrainings(trainingsList);
        super.onStop();
    }

}
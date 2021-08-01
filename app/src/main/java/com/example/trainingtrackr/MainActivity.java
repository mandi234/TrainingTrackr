package com.example.trainingtrackr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.trainingtrackr.adapters.MainAdapter;
import com.example.trainingtrackr.adapters.TrainingAdapter;
import com.example.trainingtrackr.model.Exercise;
import com.example.trainingtrackr.model.Training;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton fab;
    private static List<Training> trainingsList;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private Calendar calendar = Calendar.getInstance();
    private Date createdTrainingDate;
    private String createdTrainingName;

    public static List<Training> getTrainingsList() {
        return trainingsList;
    }

    public static void setTrainingsList(List<Training> trainingsList) {
        trainingsList = trainingsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recycler_view);
        fab = findViewById(R.id.floatingActionButton);
        trainingsList = new ArrayList<>();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
                createdTrainingDate = newDate.getTime();
                trainingsList.add(new Training(createdTrainingDate, "TEST1"));
                mainAdapter.notifyItemInserted(trainingsList.size()-1);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        fab.setOnClickListener(v -> {
            datePickerDialog.show();


        });
        initAdapter();
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
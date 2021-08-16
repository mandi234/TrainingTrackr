package com.example.trainingtrackr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.Exercise;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExcerciseViewHolder> {

    private List<Exercise> exercisesList;


    public ExercisesAdapter(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }


    @NonNull
    @NotNull
    @Override
    public ExcerciseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card_layout, parent, false);
        return new ExcerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExercisesAdapter.ExcerciseViewHolder holder, int position) {
        String exerciseName = exercisesList.get(position).getName();

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ExcerciseViewHolder extends RecyclerView.ViewHolder {



        public ExcerciseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


        }
    }
}


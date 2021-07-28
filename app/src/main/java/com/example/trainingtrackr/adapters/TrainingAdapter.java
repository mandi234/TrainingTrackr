package com.example.trainingtrackr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.Exercise;
import com.example.trainingtrackr.model.Training;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ExcerciseViewHolder> {

    private List<Exercise> exercisesList;


    public TrainingAdapter(List<Exercise> exercisesList) {
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
    public void onBindViewHolder(@NonNull @NotNull TrainingAdapter.ExcerciseViewHolder holder, int position) {
        String exerciseName = exercisesList.get(position).getName();
        holder.exerciseNameTextView.setText(exerciseName);
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ExcerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseNameTextView;

        public ExcerciseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exercise_name_tv);

        }
    }
}


package com.example.trainingtrackr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.exercise.Exercise;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExcerciseViewHolder> {

    private List<Exercise> exercisesList;
    private OnExerciseListener onExerciseListener;


    public ExercisesAdapter(List<Exercise> exercisesList, OnExerciseListener onExerciseListener) {
        this.onExerciseListener = onExerciseListener;
        this.exercisesList = exercisesList;
    }


    @NonNull
    @NotNull
    @Override
    public ExcerciseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card_layout, parent, false);
        return new ExcerciseViewHolder(itemView, onExerciseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExercisesAdapter.ExcerciseViewHolder holder, int position) {
        String exerciseName = exercisesList.get(position).getName();

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ExcerciseViewHolder extends RecyclerView.ViewHolder
        implements View.OnLongClickListener {

        private OnExerciseListener onExerciseListener;

        public ExcerciseViewHolder(@NonNull @NotNull View itemView, OnExerciseListener onExerciseListener) {
            super(itemView);

            this.onExerciseListener = onExerciseListener;
            itemView.setOnLongClickListener(this::onLongClick);
        }

        @Override
        public boolean onLongClick(View v) {
            onExerciseListener.onExerciseLongClick(getAbsoluteAdapterPosition());
            return true;
        }
    }

    public interface OnExerciseListener {
        boolean onExerciseLongClick(int position);
    }
}


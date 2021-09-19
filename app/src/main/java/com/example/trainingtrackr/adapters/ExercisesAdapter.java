package com.example.trainingtrackr.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.utils.InputParser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExcerciseViewHolder> {


    public List<Exercise> getExercisesList() {
        return exercisesList;
    }

    public void setExercisesList(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }

    private List<Exercise> exercisesList;
    private final OnExerciseListener onExerciseListener;


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
        Exercise exercise = exercisesList.get(position);
        holder.repsEditText.setText(InputParser.toExerciseFieldsText(exercise.getReps()));
        holder.setsEditText.setText(InputParser.toExerciseFieldsText(exercise.getSets()));
        holder.weightEditText.setText(InputParser.toExerciseFieldsText(exercise.getWeight()));

        String[] exercisesNames = holder.itemView.getResources().getStringArray(R.array.exercises);
        ArrayAdapter<String> exercisesNamesAdapter = new ArrayAdapter<String>(holder.itemView.getContext(), R.layout.support_simple_spinner_dropdown_item, exercisesNames);
        holder.exerciseNameAutoCompTextView.setAdapter(exercisesNamesAdapter);
        holder.exerciseNameAutoCompTextView.setText(exercise.getName());


    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public static class ExcerciseViewHolder extends RecyclerView.ViewHolder
        implements View.OnLongClickListener {

        private OnExerciseListener onExerciseListener;
        private EditText repsEditText;
        private EditText setsEditText;
        private EditText weightEditText;


        private AutoCompleteTextView exerciseNameAutoCompTextView;

        public Exercise getExercise() {
            return exercise;
        }

        private Exercise exercise;

        public OnExerciseListener getOnExerciseListener() {
            return onExerciseListener;
        }

        public void setOnExerciseListener(OnExerciseListener onExerciseListener) {
            this.onExerciseListener = onExerciseListener;
        }

        public EditText getRepsEditText() {
            return repsEditText;
        }

        public void setRepsEditText(EditText repsEditText) {
            this.repsEditText = repsEditText;
        }

        public EditText getSetsEditText() {
            return setsEditText;
        }

        public void setSetsEditText(EditText setsEditText) {
            this.setsEditText = setsEditText;
        }

        public EditText getWeightEditText() {
            return weightEditText;
        }

        public void setWeightEditText(EditText weightEditText) {
            this.weightEditText = weightEditText;
        }

        public AutoCompleteTextView getExerciseNameAutoCompTextView() {
            return exerciseNameAutoCompTextView;
        }

        public void setExerciseNameAutoCompTextView(AutoCompleteTextView exerciseNameAutoCompTextView) {
            this.exerciseNameAutoCompTextView = exerciseNameAutoCompTextView;
        }


        public ExcerciseViewHolder(@NonNull @NotNull View itemView, OnExerciseListener onExerciseListener) {
            super(itemView);

            repsEditText = itemView.findViewById(R.id.reps_etn);
            setsEditText = itemView.findViewById(R.id.sets_etn);
            weightEditText = itemView.findViewById(R.id.weight_etn);
            exerciseNameAutoCompTextView = itemView.findViewById(R.id.exerciseName_actv);

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


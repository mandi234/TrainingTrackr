package com.example.trainingtrackr.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.training.Training;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.TrainingViewHolder> {

    private Activity context;
    private List<Training> trainingList;
    private OnTrainingListener onTrainingListener;


    public TrainingsAdapter(Activity context, List<Training> trainingList, OnTrainingListener onTrainingListener) {
        this.context = context;
        this.trainingList = trainingList;
        this.onTrainingListener = onTrainingListener;
    }

    @NonNull
    @NotNull
    @Override
    public TrainingsAdapter.TrainingViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.training_card_layout, parent, false);
        return new TrainingViewHolder(rootView, onTrainingListener);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrainingsAdapter.TrainingViewHolder holder, int position) {

        Training training = trainingList.get(position);
        holder.nameTextView.setText(training.getName());
        holder.dateTextView.setText(training.getDate());
    }

    private void showTrainingMenuPopup(@NotNull TrainingsAdapter.TrainingViewHolder holder, Context ctx) {

    }

    @Override
    public int getItemCount() {
        if(trainingList == null || trainingList.isEmpty()) return 0;
        return trainingList.size();
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView dateTextView;
        private TextView nameTextView;

        public TextView getButtonViewOptions() {
            return buttonViewOptions;
        }

        private TextView buttonViewOptions;
        private OnTrainingListener onTrainingListener;

        public TrainingViewHolder(@NonNull @NotNull View itemView, OnTrainingListener onTrainingListener) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.date_tv);
            nameTextView = itemView.findViewById(R.id.name_tv);
            buttonViewOptions = itemView.findViewById(R.id.training_options_btn);

            this.onTrainingListener = onTrainingListener;
            itemView.setOnClickListener(this);
            dateTextView.setOnClickListener(this::onDateClick);
            buttonViewOptions.setOnClickListener(this::onOptionsClick);
        }

        public void onDateClick(View v) {
            onTrainingListener.onDateClick(getAbsoluteAdapterPosition());
        }

        public void onOptionsClick(View v) {
            onTrainingListener.onOptionsClick(getAbsoluteAdapterPosition());
        }

        @Override
        public void onClick(View v) {
            onTrainingListener.onTrainingClick(getAbsoluteAdapterPosition());
        }

    }

    public interface OnTrainingListener {
        void onTrainingClick(int position);
        void onDateClick(int position);
        void onOptionsClick(int position);
    }
}

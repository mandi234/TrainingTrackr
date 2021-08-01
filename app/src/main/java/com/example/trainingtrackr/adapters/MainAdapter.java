package com.example.trainingtrackr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.R;
import com.example.trainingtrackr.model.Training;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Training> trainingsList;

    public MainAdapter(List<Training> trainingsList) {
        this.trainingsList = trainingsList;
    }

    @NonNull
    @NotNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_card_layout, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainAdapter.MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trainingsList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        public MainViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}

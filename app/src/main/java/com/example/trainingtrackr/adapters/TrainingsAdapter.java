package com.example.trainingtrackr.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingtrackr.ui.trainings.TrainingsActivity;
import com.example.trainingtrackr.R;
import com.example.trainingtrackr.ui.exercies.ExercisesActivity;
import com.example.trainingtrackr.model.Training;
import com.example.trainingtrackr.ui.trainings.TrainingsViewModel;
import com.example.trainingtrackr.ui.trainings.TrainingsViewModelFactory;
import com.example.trainingtrackr.utils.InjectorUtils;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.MainViewHolder> {

    private Activity context;
    private List<Training> trainingList;

    public TrainingsAdapter(Activity context, List<Training> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @NonNull
    @NotNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.training_card_layout, parent, false);
        return new MainViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrainingsAdapter.MainViewHolder holder, int position) {

        Training training = trainingList.get(position);
        holder.nameTextView.setText(training.getName());
    }

    private void showTrainingMenuPopup(@NotNull MainViewHolder holder, Context ctx) {
        PopupMenu popup = new PopupMenu(ctx, holder.buttonViewOptions);
        popup.inflate(R.menu.training_options_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.copy_training_menu_item:
                        Toast.makeText(ctx, "TODO: Copy training", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.erase_training_menu_item:
                        Toast.makeText(ctx, "TODO: Erase training", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }

    @Override
    public int getItemCount() {
        if(trainingList == null || trainingList.isEmpty()) return 0;
        return trainingList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        private TextView nameTextView;
        private TextView buttonViewOptions;

        public MainViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.date_tv);
            nameTextView = itemView.findViewById(R.id.name_tv);
            buttonViewOptions = itemView.findViewById(R.id.training_options_btn);
        }


    }
}

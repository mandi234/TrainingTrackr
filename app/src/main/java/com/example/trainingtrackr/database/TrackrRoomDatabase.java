package com.example.trainingtrackr.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.trainingtrackr.model.exercise.Exercise;
import com.example.trainingtrackr.model.training.Training;
import com.example.trainingtrackr.model.training.dao.TrainingDao;

@Database(entities = {Training.class, Exercise.class}, version = 3)
public abstract class TrackrRoomDatabase extends RoomDatabase {
    public abstract TrainingDao trainingDao();

    /**
     * Changing the weight field in Exercise to double, because
     * I somehow didn't expect it to be double
     * Sqlite is really not convenient when modyfing columns
     */
    public static final Migration Migration_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE Exercise_new " +
                            "(id INTEGER NOT NULL," +
                            " name TEXT," +
                            " trainingId INTEGER NOT NULL," +
                            " sets INTEGER NOT NULL," +
                            " reps INTEGER NOT NULL," +
                            " weight REAL NOT NULL," +
                            " note TEXT," +
                            " PRIMARY KEY(id)," +
                            " FOREIGN KEY(trainingId) REFERENCES Training(id) ON DELETE CASCADE)"
            );

            database.execSQL(
                    "INSERT INTO Exercise_new (id, name, trainingId, sets, reps, weight, note)" +
                            "SELECT id, name, trainingId, sets, reps, weight, note from Exercise"
            );
            database.execSQL("DROP TABLE Exercise");
            database.execSQL("ALTER TABLE Exercise_new RENAME TO Exercise");
        }
    };
}

package com.example.example_4.classes.Handlers.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.example_4.JSON.Meme;

@Database(entities = {MemeEntity.class}, version = 1)
public abstract class MemeDatabase extends RoomDatabase {
    private static MemeDatabase instance;

    public abstract MemeDao memeDao();

    public  static synchronized  MemeDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MemeDatabase.class,
                    "meme_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MemeDao memeDao;

        private PopulateDbAsyncTask(MemeDatabase db) {
            memeDao = db.memeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            memeDao.insert(new MemeEntity(1,2,3,"1", "2",
                    "test", "01.01.0101,", "testAuthor,", "testGif",
                    "httest://testUrl.com"));
            return null;
        }
    }
}

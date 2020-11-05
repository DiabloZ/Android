package com.example.example_4.classes.Handlers.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.example_4.JSON.Meme;

import java.util.HashMap;
import java.util.List;

public class MemeRepository {
    private MemeDao memeDao;
    private LiveData<List<MemeEntity>> allMeme;

    public MemeRepository(Application application) {
        MemeDatabase database = MemeDatabase.getInstance(application);
        memeDao = database.memeDao();
        allMeme = memeDao.getAllMemes();
    }

    public void insert(Meme meme) {
        new MemeAsyncTask(memeDao).execute((HashMap<String, MemeEntity>) new HashMap<>().put("insert", meme));
    }

    public void update(Meme meme) {
        new MemeAsyncTask(memeDao).execute((HashMap<String, MemeEntity>) new HashMap<>().put("update", meme));
    }

    public void delete(Meme meme) {
        new MemeAsyncTask(memeDao).execute((HashMap<String, MemeEntity>) new HashMap<>().put("delete", meme));
    }

    public void deleteAllMemes() {
        new MemeAsyncTask(memeDao).execute((HashMap<String, MemeEntity>) new HashMap<>().put("delete", allMeme));
    }

    public LiveData<List<MemeEntity>> getAllMeme() {
        return allMeme;
    }

    private static class MemeAsyncTask extends AsyncTask<HashMap<String, MemeEntity>, Void, Void> {
        private MemeDao memeDao;

        private MemeAsyncTask(MemeDao memeDao) {
            this.memeDao = memeDao;
        }


        @Override
        protected Void doInBackground(HashMap<String, MemeEntity>... hashMaps) {
            if(hashMaps[0].containsKey("insert")) memeDao.insert(hashMaps[0].get("insert"));
            if(hashMaps[0].containsKey("update")) memeDao.update(hashMaps[0].get("update"));
            if(hashMaps[0].containsKey("delete")) memeDao.delete(hashMaps[0].get("delete"));
            if(hashMaps[0].containsKey("deleteAll")) memeDao.deleteAllMemes();
            return null;
        }
    }
}

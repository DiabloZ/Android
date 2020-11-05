package com.example.example_4.classes.Handlers.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.example_4.JSON.Meme;

@Database(entities = {Meme.class}, version = 1)
public abstract class DBMeme extends RoomDatabase {
    private static DBMeme instance;

    public abstract DBMemeDao memeDao();
}

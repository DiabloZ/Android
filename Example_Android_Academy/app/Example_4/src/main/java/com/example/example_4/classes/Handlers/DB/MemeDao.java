package com.example.example_4.classes.Handlers.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.example_4.JSON.Meme;

import java.util.List;

@Dao
public interface DBMemeDao {

    @Insert
    void insert(Meme meme);

    @Update
    void update(Meme meme);

    @Delete
    void delete(Meme meme);

    @Query("DELETE FROM meme_table")
    void deleteAllMemes();

    @Query("SELECT * FROM meme_table ORDER BY id DESC")
    LiveData<List<Meme>> getAllMemes();
}

package com.example.example_4.classes.Handlers.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;

@Dao
public interface MemeDao {

    @Insert
    void insert(MemeEntity meme);

    @Update
    void update(MemeEntity meme);

    @Delete
    void delete(MemeEntity meme);

    @Query("DELETE FROM meme_table")
    void deleteAllMemes();

    @Query("SELECT * FROM meme_table ORDER BY id DESC")
    LiveData<List<MemeEntity>> getAllMemes();
}

package com.suhov.memappwocompose.model.database

import androidx.room.*
import com.suhov.memappwocompose.model.Mem
import com.suhov.memappwocompose.model.MemNetworkEntity
import retrofit2.http.GET

@Dao
interface MemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (mem: Mem)

    @Insert
    fun insertAll(vararg memes:Mem)

    @Update
    fun updateMem(mem: Mem)

    @Update
    fun updateMemes(vararg memes: Mem)

    @Delete
    fun delete (mem: Mem)

    @Query("SELECT * FROM MemDB")
    fun getAll():List<Mem>

    @GET
    fun getAllMemes(): List<Mem>
}
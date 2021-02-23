package com.suhov.memappwocompose.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suhov.memappwocompose.model.Mem

@Database(entities = [Mem::class], version = 1)
abstract class MemDB: RoomDatabase() {
    abstract fun memDao():MemDao

    companion object{
        @Volatile private var INSTANCE: MemDB? = null
        private var LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MemDB::class.java, "memes_database")
                .fallbackToDestructiveMigration()
                .build()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
        }
    }
}
package com.yourdomain.mymovieapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yourdomain.mymovieapp.data.local.model.Movie

// Task 2: Room database main class
@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(ctx: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val inst = Room.databaseBuilder(
                    ctx.applicationContext,
                    AppDatabase::class.java,
                    "movies.db"
                ).build()
                INSTANCE = inst
                inst
            }
    }
}

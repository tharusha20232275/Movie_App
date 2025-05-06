package com.yourdomain.mymovieapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yourdomain.mymovieapp.data.local.model.Movie

// Task 2 & 5: Data access for movies
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<Movie>

    // Task 5: case-insensitive substring search on actors
    @Query("""
      SELECT * FROM movies
      WHERE LOWER(actors) LIKE '%' || LOWER(:query) || '%'
    """)
    suspend fun findByActor(query: String): List<Movie>
}

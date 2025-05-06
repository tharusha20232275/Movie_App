package com.yourdomain.mymovieapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Task 2: Room Entity for the movies table
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val imdbID: String,
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val ratings: String,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val type: String,
    val totalSeasons: String? = null
)

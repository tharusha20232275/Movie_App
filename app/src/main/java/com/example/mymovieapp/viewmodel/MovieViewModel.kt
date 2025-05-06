package com.yourdomain.mymovieapp.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.data.local.db.AppDatabase
import com.yourdomain.mymovieapp.data.repository.MovieRepository
import kotlinx.coroutines.launch

// Task 3 & 4: fetch from OMDb & save
class MovieViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MovieRepository(AppDatabase.getInstance(app).movieDao())

    var titleInput by mutableStateOf("")
    var fetchedMovie by mutableStateOf<Movie?>(null)
    var error by mutableStateOf<String?>(null)

    fun retrieveMovie() {
        viewModelScope.launch {
            try {
                repo.fetchMovie(titleInput)?.let {
                    fetchedMovie = it
                    error = null
                } ?: run { error = "Not found" }
            } catch (e: Exception) {
                error = e.localizedMessage
            }
        }
    }

    fun saveMovie() {
        fetchedMovie?.let {
            viewModelScope.launch { repo.insert(it) }
        }
    }
}

package com.yourdomain.mymovieapp.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yourdomain.mymovieapp.data.local.db.AppDatabase
import com.yourdomain.mymovieapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Task 7: search titles online
class TitleSearchViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MovieRepository(AppDatabase.getInstance(app).movieDao())

    var titleQuery by mutableStateOf("")
    var onlineResults by mutableStateOf<List<com.yourdomain.mymovieapp.data.local.model.Movie>>(emptyList())

    fun searchOnline() {
        viewModelScope.launch {
            onlineResults = withContext(Dispatchers.IO) {
                repo.searchTitles(titleQuery)
            }
        }
    }
}

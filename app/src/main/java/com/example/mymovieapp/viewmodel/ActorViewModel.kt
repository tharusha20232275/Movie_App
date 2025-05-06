package com.yourdomain.mymovieapp.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yourdomain.mymovieapp.data.local.db.AppDatabase
import com.yourdomain.mymovieapp.data.local.model.Movie
import kotlinx.coroutines.launch

// Task 5: search local DB by actor substring
class ActorViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.getInstance(app).movieDao()

    var actorQuery by mutableStateOf("")
    var results by mutableStateOf<List<Movie>>(emptyList())

    fun search() {
        viewModelScope.launch {
            results = dao.findByActor(actorQuery)
        }
    }
}

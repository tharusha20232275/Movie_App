package com.yourdomain.mymovieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Task 1 & 7: initial menu with 4 buttons
@Composable
fun HomeScreen(
    onAddMovies: () -> Unit,
    onSearchMovie: () -> Unit,
    onSearchActor: () -> Unit,
    onSearchTitles: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onAddMovies, modifier = Modifier.fillMaxWidth()) {
            Text("Add Movies to DB")
        }
        Button(onClick = onSearchMovie, modifier = Modifier.fillMaxWidth()) {
            Text("Search for Movies")
        }
        Button(onClick = onSearchActor, modifier = Modifier.fillMaxWidth()) {
            Text("Search for Actors")
        }
        Button(onClick = onSearchTitles, modifier = Modifier.fillMaxWidth()) {
            Text("Search Titles Online")
        }
    }
}

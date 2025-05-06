package com.yourdomain.mymovieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.viewmodel.MovieViewModel

@Composable
fun SearchMovieScreen(vm: MovieViewModel) {
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = vm.titleInput,
            onValueChange = { vm.titleInput = it },
            label = { Text("Movie Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row {
            Button(onClick = { vm.retrieveMovie() }) { Text("Retrieve Movie") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { vm.saveMovie() }) { Text("Save Movie to Database") }
        }
        vm.error?.let { Text("Error: $it") }
        vm.fetchedMovie?.let { m: Movie ->
            Spacer(Modifier.height(16.dp))
            Text("Title: ${m.title}")
            Text("Year: ${m.year}")
            Text("Rated: ${m.rated}")
            Text("Released: ${m.released}")
            Text("Runtime: ${m.runtime}")
            Text("Genre: ${m.genre}")
            Text("Director: ${m.director}")
            Text("Writer: ${m.writer}")
            Text("Actors: ${m.actors}")
            Text("Plot: ${m.plot}")
            Text("Language: ${m.language}")
            Text("Country: ${m.country}")
            Text("Awards: ${m.awards}")
            Text("Metascore: ${m.metascore}")
            Text("IMDb Rating: ${m.imdbRating}")
            Text("IMDb Votes: ${m.imdbVotes}")
            Text("Type: ${m.type}")
            m.totalSeasons?.let { Text("Total Seasons: $it") }
        }
    }
}

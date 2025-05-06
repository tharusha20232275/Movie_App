package com.yourdomain.mymovieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.viewmodel.ActorViewModel

// Task 5: search local DB by actor substring
@Composable
fun SearchActorScreen(vm: ActorViewModel) {
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = vm.actorQuery,
            onValueChange = { vm.actorQuery = it },
            label = { Text("Actor Name Substring") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { vm.search() }) {
            Text("Search")
        }
        Spacer(Modifier.height(16.dp))
        if (vm.results.isEmpty()) {
            Text("No results", style = MaterialTheme.typography.subtitle1)
        } else {
            LazyColumn {
                items(vm.results) { m: Movie ->
                    Text("${m.title} (${m.year})")
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

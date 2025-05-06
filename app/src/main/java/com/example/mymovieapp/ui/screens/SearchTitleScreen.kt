package com.yourdomain.mymovieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.viewmodel.TitleSearchViewModel

// Task 7: query OMDb by title substring (online)
@Composable
fun SearchTitleScreen(vm: TitleSearchViewModel) {
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = vm.titleQuery,
            onValueChange = { vm.titleQuery = it },
            label = { Text("Title Substring") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { vm.searchOnline() }) {
            Text("Search")
        }
        Spacer(Modifier.height(16.dp))
        if (vm.onlineResults.isEmpty()) {
            Text("No results", style = MaterialTheme.typography.subtitle1)
        } else {
            LazyColumn {
                items(vm.onlineResults) { m: Movie ->
                    Text("${m.title} (${m.year})")
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

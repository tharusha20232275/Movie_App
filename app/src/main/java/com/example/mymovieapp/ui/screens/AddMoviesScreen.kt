package com.yourdomain.mymovieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourdomain.mymovieapp.viewmodel.HomeViewModel

// Task 2: UI to trigger hard-coded DB population
@Composable
fun AddMoviesScreen(vm: HomeViewModel, onDone: () -> Unit) {
    var loading by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
            Spacer(Modifier.height(16.dp))
            Text("Adding movies…")
        } else {
            Button(onClick = {
                loading = true
                vm.addHardcodedMovies {
                    loading = false
                    onDone()
                }
            }) {
                Text("Add Movies to DB")
            }
        }
    }
}

package com.yourdomain.mymovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.yourdomain.mymovieapp.ui.screens.*
import com.yourdomain.mymovieapp.viewmodel.*

@Composable
fun NavGraph() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onAddMovies    = { nav.navigate("add") },
                onSearchMovie  = { nav.navigate("searchMovie") },
                onSearchActor  = { nav.navigate("searchActor") },
                onSearchTitles = { nav.navigate("searchTitle") }
            )
        }
        composable("add") {
            val vm: HomeViewModel = viewModel()
            AddMoviesScreen(vm) { nav.popBackStack() }
        }
        composable("searchMovie") {
            val vm: MovieViewModel = viewModel()
            SearchMovieScreen(vm)
        }
        composable("searchActor") {
            val vm: ActorViewModel = viewModel()
            SearchActorScreen(vm)
        }
        composable("searchTitle") {
            val vm: TitleSearchViewModel = viewModel()
            SearchTitleScreen(vm)
        }
    }
}

package com.yourdomain.mymovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yourdomain.mymovieapp.navigation.NavGraph
import com.yourdomain.mymovieapp.ui.theme.MyMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMovieAppTheme {
                NavGraph()
            }
        }
    }
}

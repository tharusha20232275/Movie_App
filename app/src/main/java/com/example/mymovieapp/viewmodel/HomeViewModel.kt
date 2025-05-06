package com.yourdomain.mymovieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yourdomain.mymovieapp.data.local.db.AppDatabase
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.data.repository.MovieRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MovieRepository(AppDatabase.getInstance(app).movieDao())

    fun addHardcodedMovies(onComplete: () -> Unit) {
        viewModelScope.launch {
            parseHardcoded().forEach { repo.insert(it) }
            onComplete()
        }
    }

    private fun parseHardcoded(): List<Movie> {
        // Explicitly a List<String> so JSONObject(String) is unambiguous
        val jsonList: List<String> = listOf(
            """
            {
              "Title":"The Shawshank Redemption",
              "Year":"1994",
              "Rated":"R",
              "Released":"14 Oct 1994",
              "Runtime":"142 min",
              "Genre":"Drama",
              "Director":"Frank Darabont",
              "Writer":"Stephen King, Frank Darabont",
              "Actors":"Tim Robbins, Morgan Freeman, Bob Gunton",
              "Plot":"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
              "Language":"English",
              "Country":"USA",
              "Awards":"7 wins",
              "Ratings":[{"Source":"Internet Movie Database","Value":"9.3/10"}],
              "Metascore":"80",
              "imdbRating":"9.3",
              "imdbVotes":"2700000",
              "imdbID":"tt0111161",
              "Type":"movie",
              "totalSeasons":null
            }
            """.trimIndent(),
            // …(other JSON strings for your 4 remaining movies)…
        )

        return jsonList.map { jsonStr ->
            val j = JSONObject(jsonStr)  // jsonStr is definitely a String here
            Movie(
                imdbID      = j.optString("imdbID"),
                title       = j.optString("Title"),
                year        = j.optString("Year"),
                rated       = j.optString("Rated"),
                released    = j.optString("Released"),
                runtime     = j.optString("Runtime"),
                genre       = j.optString("Genre"),
                director    = j.optString("Director"),
                writer      = j.optString("Writer"),
                actors      = j.optString("Actors"),
                plot        = j.optString("Plot"),
                language    = j.optString("Language"),
                country     = j.optString("Country"),
                awards      = j.optString("Awards"),
                ratings     = j.optJSONArray("Ratings")?.toString() ?: "[]",
                metascore   = j.optString("Metascore"),
                imdbRating  = j.optString("imdbRating"),
                imdbVotes   = j.optString("imdbVotes"),
                type        = j.optString("Type"),
                totalSeasons = if (j.has("totalSeasons") && !j.isNull("totalSeasons"))
                    j.optString("totalSeasons")
                else null
            )
        }
    }
}

// File: app/src/main/java/com/yourdomain/mymovieapp/viewmodel/HomeViewModel.kt

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
        // Each string here is one full JSON object matching OMDb's fields.
        val jsonList = listOf(
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

            """
            {
              "Title":"Batman: The Dark Knight Returns, Part 1",
              "Year":"2012",
              "Rated":"PG-13",
              "Released":"25 Sep 2012",
              "Runtime":"76 min",
              "Genre":"Animation, Action, Crime, Drama, Thriller",
              "Director":"Jay Oliva",
              "Writer":"Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob Goodman",
              "Actors":"Peter Weller, Ariel Winter, David Selby, Wade Williams",
              "Plot":"Batman has not been seen for ten years. A new breed of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back into the cape and cowl. But, does he still have what it takes to fight crime in a new era?",
              "Language":"English",
              "Country":"USA",
              "Awards":"",
              "Ratings":[],
              "Metascore":"",
              "imdbRating":"",
              "imdbVotes":"",
              "imdbID":"tt2313197",
              "Type":"movie",
              "totalSeasons":null
            }
            """.trimIndent(),

            """
            {
              "Title":"The Lord of the Rings: The Return of the King",
              "Year":"2003",
              "Rated":"PG-13",
              "Released":"17 Dec 2003",
              "Runtime":"201 min",
              "Genre":"Action, Adventure, Drama",
              "Director":"Peter Jackson",
              "Writer":"J.R.R. Tolkien, Fran Walsh, Philippa Boyens",
              "Actors":"Elijah Wood, Viggo Mortensen, Ian McKellen",
              "Plot":"Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
              "Language":"English",
              "Country":"New Zealand, USA",
              "Awards":"11 wins",
              "Ratings":[{"Source":"Internet Movie Database","Value":"8.9/10"}],
              "Metascore":"94",
              "imdbRating":"8.9",
              "imdbVotes":"1,700,000",
              "imdbID":"tt0167260",
              "Type":"movie",
              "totalSeasons":null
            }
            """.trimIndent(),

            """
            {
              "Title":"Inception",
              "Year":"2010",
              "Rated":"PG-13",
              "Released":"16 Jul 2010",
              "Runtime":"148 min",
              "Genre":"Action, Adventure, Sci-Fi",
              "Director":"Christopher Nolan",
              "Writer":"Christopher Nolan",
              "Actors":"Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
              "Plot":"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
              "Language":"English",
              "Country":"USA, UK",
              "Awards":"4 wins",
              "Ratings":[{"Source":"Internet Movie Database","Value":"8.8/10"}],
              "Metascore":"74",
              "imdbRating":"8.8",
              "imdbVotes":"2,200,000",
              "imdbID":"tt1375666",
              "Type":"movie",
              "totalSeasons":null
            }
            """.trimIndent(),

            """
            {
              "Title":"The Matrix",
              "Year":"1999",
              "Rated":"R",
              "Released":"31 Mar 1999",
              "Runtime":"136 min",
              "Genre":"Action, Sci-Fi",
              "Director":"Lana Wachowski, Lilly Wachowski",
              "Writer":"Lilly Wachowski, Lana Wachowski",
              "Actors":"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
              "Plot":"When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
              "Language":"English",
              "Country":"USA",
              "Awards":"4 wins",
              "Ratings":[{"Source":"Internet Movie Database","Value":"8.7/10"}],
              "Metascore":"73",
              "imdbRating":"8.7",
              "imdbVotes":"1,600,000",
              "imdbID":"tt0133093",
              "Type":"movie",
              "totalSeasons":null
            }
            """.trimIndent()
        )

        return jsonList.map { jsonStr ->
            val j = JSONObject(jsonStr)
            Movie(
                imdbID     = j.optString("imdbID"),
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
                totalSeasons= j.optString("totalSeasons", null)
            )
        }
    }
}

package com.yourdomain.mymovieapp.data.repository

import com.yourdomain.mymovieapp.data.local.db.MovieDao
import com.yourdomain.mymovieapp.data.local.model.Movie
import com.yourdomain.mymovieapp.data.remote.OmdbApiService
import org.json.JSONArray

// Centralized data operations
class MovieRepository(private val dao: MovieDao) {

    suspend fun insert(movie: Movie) = dao.insert(movie)
    suspend fun getAllLocal() = dao.getAll()
    suspend fun findByActor(query: String) = dao.findByActor(query)

    // Task 3: fetch & map JSON → Movie
    fun fetchMovie(title: String): Movie? {
        val json = OmdbApiService.fetchMovieByTitle(title)
        if (json.optString("Response") != "True") return null
        val ratingsArr = json.optJSONArray("Ratings") ?: JSONArray()
        return Movie(
            imdbID      = json.optString("imdbID"),
            title       = json.optString("Title"),
            year        = json.optString("Year"),
            rated       = json.optString("Rated"),
            released    = json.optString("Released"),
            runtime     = json.optString("Runtime"),
            genre       = json.optString("Genre"),
            director    = json.optString("Director"),
            writer      = json.optString("Writer"),
            actors      = json.optString("Actors"),
            plot        = json.optString("Plot"),
            language    = json.optString("Language"),
            country     = json.optString("Country"),
            awards      = json.optString("Awards"),
            ratings     = ratingsArr.toString(),
            metascore   = json.optString("Metascore"),
            imdbRating  = json.optString("imdbRating"),
            imdbVotes   = json.optString("imdbVotes"),
            type        = json.optString("Type"),
            totalSeasons = if (json.has("totalSeasons")) json.optString("totalSeasons") else null
        )
    }


    // Task 7: search list of titles (first 10)
    fun searchTitles(substring: String): List<Movie> {
        val json = OmdbApiService.searchMovies(substring)
        if (json.optString("Response") != "True") return emptyList()
        val arr = json.getJSONArray("Search")
        val results = mutableListOf<Movie>()
        for (i in 0 until minOf(10, arr.length())) {
            val o = arr.getJSONObject(i)
            results += Movie(
                imdbID = o.getString("imdbID"),
                title  = o.getString("Title"),
                year   = o.getString("Year"),
                rated       = "", released="", runtime="", genre="",
                director    = "", writer="", actors="", plot="",
                language    = "", country="", awards="",
                ratings     = "[]", metascore="", imdbRating="",
                imdbVotes   = "", type=o.getString("Type"), totalSeasons=null
            )
        }
        return results
    }
}

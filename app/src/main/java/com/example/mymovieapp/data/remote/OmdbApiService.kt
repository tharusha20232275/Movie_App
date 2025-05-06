package com.yourdomain.mymovieapp.data.remote

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

// Task 3 & 7: Simple HTTP + JSON helper
object OmdbApiService {
    fun fetchMovieByTitle(title: String): JSONObject {
        val url = URL("$OMDB_BASE_URL?t=${title.trim().replace(" ", "+")}&apikey=$OMDB_API_KEY")
        val conn = (url.openConnection() as HttpURLConnection).apply { requestMethod = "GET" }
        val text = conn.inputStream.bufferedReader().readText()
        return JSONObject(text)
    }

    fun searchMovies(query: String): JSONObject {
        val url = URL("$OMDB_BASE_URL?s=${query.trim().replace(" ", "+")}&apikey=$OMDB_API_KEY")
        val conn = (url.openConnection() as HttpURLConnection).apply { requestMethod = "GET" }
        val text = conn.inputStream.bufferedReader().readText()
        return JSONObject(text)
    }
}

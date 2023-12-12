package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository:MovieRepository {

    private val movieList = mutableListOf<Movie>()

    init {
        movieList.add(Movie(1,"ksakf","akfslas","kasfka","jlfsa"))
        movieList.add(Movie(2,"ksakf","akfslas","kasfka","jlfsa"))

    }

    override suspend fun getMovies(): List<Movie>? {
       return movieList
    }

    override suspend fun updateMovies(): List<Movie>? {
        movieList.clear()
        movieList.add(Movie(3,"ksakf","akfslas","kasfka","jlfsa"))
        movieList.add(Movie(4,"ksakf","akfslas","kasfka","jlfsa"))
        return movieList
    }
}
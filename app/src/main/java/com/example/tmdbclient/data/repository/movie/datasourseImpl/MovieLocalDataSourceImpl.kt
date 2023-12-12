package com.example.tmdbclient.data.repository.movie.datasourseImpl

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> = movieDao.getAllMovies()



    override suspend fun saveMoviesToDataBase(movies: List<Movie>) {
        CoroutineScope(IO).launch {movieDao.saveMovies(movies)  }
    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch { movieDao.deleteAllMovies() }
    }
}
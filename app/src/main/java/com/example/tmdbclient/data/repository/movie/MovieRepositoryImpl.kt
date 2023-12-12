package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
):MovieRepository {


    override suspend fun getMovies(): List<Movie>? {
        return  getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
       val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDataBase(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI():List<Movie>{
        lateinit var movieList : List<Movie>
        try {
            val  response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body!=null){
                movieList = body.movies
            }

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }

        return movieList
    }

    suspend fun getMoviesFromDB():List<Movie>{
        lateinit var movieList : List<Movie>
        try {
        movieList = movieLocalDataSource.getMoviesFromDB()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (movieList.size>0){
            return movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDataBase(movieList)
        }
        return  movieList
    }

    suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList : List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (movieList.size>0){
            return movieList
        }else{
            movieList = getMoviesFromDB()
           movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return  movieList
    }


}
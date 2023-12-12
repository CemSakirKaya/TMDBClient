package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET

interface TMDBService {
    //https://api.themoviedb.org/3/movie/popular?api_key=your_api_key

    @GET("movie/popular?api_key=2838bb42be9ae6f575c2316f7c5798e4")
    suspend fun getPopularMovies():Response<MovieList>


    @GET("tv/popular?api_key=2838bb42be9ae6f575c2316f7c5798e4")
    suspend fun getPopularTvShows( ):Response<TvShowList>

    @GET("person/popular?api_key=2838bb42be9ae6f575c2316f7c5798e4")
    suspend fun getPopularArtists():Response<ArtistList>


}
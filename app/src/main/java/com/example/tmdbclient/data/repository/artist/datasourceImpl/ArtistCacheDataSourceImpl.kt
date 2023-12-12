package com.example.tmdbclient.data.repository.artist.datasourceImpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource

class ArtistCacheDataSourceImpl: ArtistCacheDataSource {
    private var artistlist= ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> {
     return artistlist
    }

    override suspend fun saveArtistsToCache(movies: List<Artist>) {
        artistlist.clear()
        artistlist = ArrayList(movies)
    }
}
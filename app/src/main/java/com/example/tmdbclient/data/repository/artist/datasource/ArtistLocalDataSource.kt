package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie

interface ArtistLocalDataSource {

    suspend fun getArtistFromDB():List<Artist>
    suspend fun saveArtistToDataBase(artists:List<Artist>)
    suspend fun clearAll()
}
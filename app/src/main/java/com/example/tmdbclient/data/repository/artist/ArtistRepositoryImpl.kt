package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository

class ArtistRepositoryImpl(
    private val artistCacheDataSource: ArtistCacheDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistRemoteDataSource: ArtistRemoteDataSource
):ArtistRepository {


    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtist(): List<Artist>? {
       val newListOfArtists =getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDataBase(newListOfArtists)
       artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList : List<Artist>
        try {
            val  response = artistRemoteDataSource.getArtist()
            val body = response.body()
            if (body!=null){
                artistList = body.artists
            }

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }

        return artistList
    }

    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList : List<Artist>
        try {
        artistList = artistLocalDataSource.getArtistFromDB()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (artistList.size>0){
            return artistList
        }else{
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistToDataBase(artistList)
        }
        return  artistList
    }

    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList : List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (artistList.size>0){
            return artistList
        }else{
            artistList = getArtistsFromDB()
           artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return  artistList
    }


}
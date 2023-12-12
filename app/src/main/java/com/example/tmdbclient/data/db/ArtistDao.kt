package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDao {

    @Query("SELECT * FROM popular_artists")
    suspend fun getAllArtist():List<Artist>

    @Query("DELETE FROM popular_artists")
    suspend fun deleteAllArtist()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtist(artists : List<Artist>)

}
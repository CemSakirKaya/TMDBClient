package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM popular_tvShows")
    suspend fun getAllTvShows():List<TvShow>

    @Query("DELETE FROM popular_tvShows")
    suspend fun deleteAllTvShows()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShows:List<TvShow>)

}
package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvshowDao: TvShowDao) : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> = tvshowDao.getAllTvShows()



    override suspend fun saveTvShowsToDataBase(tvshows: List<TvShow>) {
        CoroutineScope(IO).launch {tvshowDao.saveTvShows(tvshows)  }
    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch { tvshowDao.deleteAllTvShows() }
    }
}
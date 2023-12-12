package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {
    private var tvShowList= ArrayList<TvShow>()
    override suspend fun getTvShowsFromCache(): List<TvShow> {
     return tvShowList
    }

    override suspend fun saveTvShowsToCache(tvshows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvshows)
    }
}
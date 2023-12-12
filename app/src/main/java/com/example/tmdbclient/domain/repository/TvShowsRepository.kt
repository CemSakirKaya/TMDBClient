package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowsRepository {

    suspend fun getTvShows():List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}
package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowsRepository

class TvShowRepositoryImpl(
    private val tvShowCacheDataSource: TvShowCacheDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
):TvShowsRepository {


    override suspend fun getTvShows(): List<TvShow>? {
        return  getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
       val newListOfTvShows = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
       tvShowLocalDataSource.saveTvShowsToDataBase(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI():List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            val  response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body!=null){
                tvShowList = body.tvShows
            }

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }

        return tvShowList
    }

    suspend fun getTvShowsFromDB():List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
        tvShowList = tvShowLocalDataSource.getTvShowsFromDB()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (tvShowList.size>0){
            return tvShowList
        }else{
            tvShowList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDataBase(tvShowList)
        }
        return  tvShowList
    }

    suspend fun getTvShowsFromCache():List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()

        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        if (tvShowList.size>0){
            return tvShowList
        }else{
            tvShowList = getTvShowsFromDB()
           tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return  tvShowList
    }


}
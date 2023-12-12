package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource,
    movieCacheDataSource: MovieCacheDataSource,
    movieLocalDataSource: MovieLocalDataSource):MovieRepository{
        return MovieRepositoryImpl(movieCacheDataSource,movieLocalDataSource,movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(artistRemoteDataSource: ArtistRemoteDataSource,
                               artistCacheDataSource: ArtistCacheDataSource,
                               artistLocalDataSource: ArtistLocalDataSource):ArtistRepository{
        return ArtistRepositoryImpl(artistCacheDataSource,artistLocalDataSource,artistRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideTvShowsRepository(tvShowRemoteDataSource: TvShowRemoteDataSource,
                              tvShowLocalDataSource: TvShowLocalDataSource,
                               tvShowCacheDataSource: TvShowCacheDataSource):TvShowsRepository{
        return TvShowRepositoryImpl(tvShowCacheDataSource,tvShowLocalDataSource,tvShowRemoteDataSource)
    }


}
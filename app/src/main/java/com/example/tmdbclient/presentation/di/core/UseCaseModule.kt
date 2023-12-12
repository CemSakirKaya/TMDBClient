package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowsRepository
import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {


        @Provides
        fun provideGetMovieUseCase(movieRepository: MovieRepository):GetMoviesUseCase{
            return GetMoviesUseCase(movieRepository)
        }


    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository):UpdateMoviesUseCase{
        return UpdateMoviesUseCase(movieRepository)
    }


    @Provides
    fun provideGetTvShowUseCase(tvShowsRepository: TvShowsRepository):GetTvShowsUseCase{
        return GetTvShowsUseCase(tvShowsRepository)
    }


    @Provides
    fun provideUpdateTvShowUseCase(tvShowsRepository: TvShowsRepository):UpdateTvShowsUseCase{
        return UpdateTvShowsUseCase(tvShowsRepository)
    }


    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository):GetArtistsUseCase{
        return GetArtistsUseCase(artistRepository)
    }


    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository):UpdateArtistsUseCase{
        return UpdateArtistsUseCase(artistRepository)
    }


}
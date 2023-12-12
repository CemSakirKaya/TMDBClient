package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import com.example.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory
                (getTvShowsUseCase: GetTvShowsUseCase,updateTvShowsUseCase: UpdateTvShowsUseCase)
                 :TvShowViewModelFactory{
            return TvShowViewModelFactory(getTvShowsUseCase,updateTvShowsUseCase)
    }


}
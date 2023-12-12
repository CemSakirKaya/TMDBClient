package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory
                (getArtistsUseCase: GetArtistsUseCase,updateArtistsUseCase: UpdateArtistsUseCase)
                 :ArtistViewModelFactory{
            return ArtistViewModelFactory(getArtistsUseCase,updateArtistsUseCase)
    }


}
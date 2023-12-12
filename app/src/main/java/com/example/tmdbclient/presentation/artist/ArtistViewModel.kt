package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase

class ArtistViewModel(private val getArtistsUseCase: GetArtistsUseCase
    ,private val updateArtistsUseCase: UpdateArtistsUseCase):ViewModel() {



        fun getArtist() = liveData {
         val  artistList = getArtistsUseCase.execute()
            emit(artistList) }


        fun updateArtist() = liveData {
        val  artistList = updateArtistsUseCase.execute()
              emit(artistList) }



}
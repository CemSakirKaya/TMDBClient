package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel(private val getTvShowsUseCase: GetTvShowsUseCase,
                      private val updateTvShowsUseCase: UpdateTvShowsUseCase) : ViewModel()
{

             fun getTvShows() = liveData {
              val TvShowList =  getTvShowsUseCase.execute()
             emit(TvShowList)
                }

                fun updateTvShows() = liveData {
                val TvShowList =  updateTvShowsUseCase.execute()
             emit(TvShowList)
             }


}
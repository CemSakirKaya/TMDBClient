package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase= GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)

        movieViewModel = MovieViewModel(getMoviesUseCase,updateMoviesUseCase)
    }

    @Test
    fun getMovie_returnCurrentList(){

         val movieList = mutableListOf<Movie>()
            movieList.add(Movie(1,"ksakf","akfslas","kasfka","jlfsa"))
            movieList.add(Movie(2,"ksakf","akfslas","kasfka","jlfsa"))


        val list = movieViewModel.getMovies().getOrAwaitValue()

        assertThat(list).isEqualTo(movieList)
    }

    @Test
    fun updateMovie_returnCurrentList(){

        val movieList = mutableListOf<Movie>()
        movieList.add(Movie(3,"ksakf","akfslas","kasfka","jlfsa"))
        movieList.add(Movie(4,"ksakf","akfslas","kasfka","jlfsa"))


        val list = movieViewModel.updateMovies().getOrAwaitValue()

        assertThat(list).isEqualTo(movieList)
    }




}
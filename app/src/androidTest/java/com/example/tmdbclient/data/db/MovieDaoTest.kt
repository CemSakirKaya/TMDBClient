package com.example.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val ınstanTaskExecuterRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var database :TMDBDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),TMDBDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        movieDao = database.movieDao()
    }


    @Test
    fun saveMoviesTest() = runBlocking{


        val movies = listOf(
         Movie(1,"kdlflkda","jlkaflk","askaf","fsakas"),
       Movie(2,"kdlflkda","jlkaflk","askaf","fsakas"),
          Movie(3,"kdlflkda","jlkaflk","askaf","fsakas"),
        Movie(4,"kdlflkda","jlkaflk","askaf","fsakas")
        )

        movieDao.saveMovies(movies)

        val  allmovies = movieDao.getAllMovies()

        Truth.assertThat(allmovies).isEqualTo(movies)
    }

    @Test
    fun deleteAllMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1,"kdlflkda","jlkaflk","askaf","fsakas"),
            Movie(2,"kdlflkda","jlkaflk","askaf","fsakas"),
            Movie(3,"kdlflkda","jlkaflk","askaf","fsakas"),
            Movie(4,"kdlflkda","jlkaflk","askaf","fsakas")
        )
        movieDao.saveMovies(movies)

        movieDao.deleteAllMovies()

        val  list = movieDao.getAllMovies()
        Truth.assertThat(list).isEmpty()


    }










    @After
    fun tearDown() {
        database.close()
    }
}
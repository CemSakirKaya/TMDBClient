package com.example.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)

        (application as Injector)
            .createmovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.subtitle = "Movies"
        initrv()


    }

    private fun initrv(){
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.setHasFixedSize(true)
        adapter = MovieAdapter()
        binding.rv.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()

        responseLiveData.observe(this,{
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar2.visibility = View.GONE
            }else{
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(applicationContext,"No data available",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateMovies(){
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.updateMovies()

        responseLiveData.observe(this,{
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar2.visibility = View.GONE
            }else{
                binding.progressBar2.visibility = View.GONE
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.update_xml,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_update ->{
                updateMovies()
                return true
            }
            else->  return  super.onOptionsItemSelected(item)

        }

    }






}
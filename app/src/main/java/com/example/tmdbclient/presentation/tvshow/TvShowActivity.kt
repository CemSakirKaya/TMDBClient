package com.example.tmdbclient.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.movie.MovieAdapter
import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private lateinit var binding:ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_tv_show)

        (application as Injector)
            .createtvShowSubComponent()
            .inject(this)

        tvShowViewModel = ViewModelProvider(this,factory).get(TvShowViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.subtitle = "Tv Shows"
        initrv()

    }

    private fun initrv(){
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.setHasFixedSize(true)
        adapter = TvShowAdapter()
        binding.rv.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShows()

        responseLiveData.observe(this,{
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar2.visibility = View.GONE
            }else{
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(applicationContext,"No data available", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateMovies(){
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.updateTvShows()

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
        val inflater : MenuInflater = menuInflater
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
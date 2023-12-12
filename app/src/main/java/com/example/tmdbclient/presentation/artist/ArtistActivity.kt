package com.example.tmdbclient.presentation.artist

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
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.movie.MovieAdapter
import com.example.tmdbclient.presentation.movie.MovieViewModel
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var binding:ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_artist)

        (application as Injector)
            .createartistSubComponent()
            .inject(this)

        artistViewModel = ViewModelProvider(this,factory).get(ArtistViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.subtitle = "Artists"
        initrv()


    }


    private fun initrv(){
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.setHasFixedSize(true)
        adapter = ArtistAdapter()
        binding.rv.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtist()

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
        val responseLiveData = artistViewModel.updateArtist()

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
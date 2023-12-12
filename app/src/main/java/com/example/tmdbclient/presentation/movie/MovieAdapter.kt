package com.example.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListLayoutBinding

class MovieAdapter() :RecyclerView.Adapter<myViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setList(movies:List<Movie>){
         movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListLayoutBinding
        = DataBindingUtil.inflate(layoutInflater, R.layout.list_layout,parent,false)

        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

            holder.bind(movieList[position])
    }

}

class myViewHolder(val binding:ListLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movie:Movie){
        binding.titleTextView.text = movie.title.toString()
        binding.descriptionTextView.text = movie.overview.toString()
        val posterUrl = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Glide.with(binding.imageView.context)
            .load(posterUrl)
            .into(binding.imageView)


    }

}
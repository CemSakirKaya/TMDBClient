package com.example.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.databinding.ListLayoutBinding

class TvShowAdapter() :RecyclerView.Adapter<myViewHolder>() {

    private val tvList = ArrayList<TvShow>()

    fun setList(movies:List<TvShow>){
         tvList.clear()
        tvList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListLayoutBinding
        = DataBindingUtil.inflate(layoutInflater, R.layout.list_layout,parent,false)

        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

            holder.bind(tvList[position])
    }

}

class myViewHolder(val binding:ListLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(tvShow: TvShow){
        binding.titleTextView.text = tvShow.name.toString()
        binding.descriptionTextView.text = tvShow.overview.toString()
        val posterUrl = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
        Glide.with(binding.imageView.context)
            .load(posterUrl)
            .into(binding.imageView)


    }

}
package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListLayoutBinding

class ArtistAdapter() :RecyclerView.Adapter<myViewHolder>() {

    private val artistList = ArrayList<Artist>()

    fun setList(artists:List<Artist>){
         artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListLayoutBinding
        = DataBindingUtil.inflate(layoutInflater, R.layout.list_layout,parent,false)

        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

            holder.bind(artistList[position])
    }

}

class myViewHolder(val binding:ListLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(artist:Artist){
        binding.titleTextView.text = artist.name.toString()
        binding.descriptionTextView.text = artist.popularity.toString()
        val posterUrl = "https://image.tmdb.org/t/p/w500" + artist.profilePath
        Glide.with(binding.imageView.context)
            .load(posterUrl)
            .into(binding.imageView)


    }

}
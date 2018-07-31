package com.example.alan_pc.androidmoviedb.presentation.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_card.view.*

/**
 * Created by ALAN-PC on 29/07/2018
 */
class MovieAdapter(val movieList: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun addMovieToList(movieList: MutableList<Movie>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) = with(itemView) {
            movieTitle.text = item.original_title
            movieAverage.text = item.vote_average.toInt().toString()
            Picasso.get().load("http://image.tmdb.org/t/p/w500" + item.backdrop_path).error(R.mipmap.grumpy_404).fit().centerCrop().into(movieCoverIv)
        }
    }


    fun clearAdapter() {
        if(this.movieList.size>0) this.movieList.clear()
    }
}
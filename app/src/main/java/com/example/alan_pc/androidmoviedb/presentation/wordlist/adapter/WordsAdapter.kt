package com.example.alan_pc.androidmoviedb.presentation.wordlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alan_pc.androidmoviedb.R
import kotlinx.android.synthetic.main.item_words.view.*

/**
 * Created by ALAN-PC on 29/07/2018
 */
class WordsAdapter(val words: MutableList<String>) : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_words, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) = with(itemView) {
            wordTitle.text = item
        }
    }
}
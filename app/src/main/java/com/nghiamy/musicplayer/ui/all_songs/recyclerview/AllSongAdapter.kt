package com.nghiamy.musicplayer.ui.all_songs.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nghiamy.musicplayer.R

class AllSongAdapter() : RecyclerView.Adapter<AllSongViewHolder>() {
     lateinit var list:ArrayList<HashMap<String,String>>

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AllSongViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_all_song, parent, false).also {
            return AllSongViewHolder(it)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllSongViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
}
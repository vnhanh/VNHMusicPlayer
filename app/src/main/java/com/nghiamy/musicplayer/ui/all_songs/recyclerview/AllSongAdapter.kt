package com.nghiamy.musicplayer.ui.all_songs.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.datasource.song.SongRepository
import com.nghiamy.musicplayer.base.model.Song

class AllSongAdapter(val repository: SongRepository) : RecyclerView.Adapter<AllSongViewHolder>() {

     private var list:ArrayList<Song> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AllSongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_all_songs, parent, false)

        val vh = AllSongViewHolder(view, repository)

        return vh
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllSongViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setListMusic(musicList: ArrayList<Song>) {
        list = musicList
        notifyDataSetChanged()
    }
}
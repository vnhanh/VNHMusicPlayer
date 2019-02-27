package com.nghiamy.musicplayer.ui.all_songs.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import com.nghiamy.musicplayer.base.common.Constant.Companion.SONG_PATH
import com.nghiamy.musicplayer.base.common.Constant.Companion.SONG_TITLE
import kotlinx.android.synthetic.main.view_holder_all_song.view.*
import java.util.HashMap

class AllSongViewHolder(val view:View) : RecyclerView.ViewHolder(view) {

    fun bind(item: HashMap<String, String>) {
        val title = item.get(SONG_TITLE)
        val path = item.get(SONG_PATH)

        displayUI(title)
    }

    private fun displayUI(title: String?) {
        view.tv_name_music.text = title

    }
}
package com.nghiamy.musicplayer.ui.all_songs.recyclerview

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.common.Util
import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.service.Key
import com.nghiamy.musicplayer.base.service.PlayMusicService
import kotlinx.android.synthetic.main.view_holder_all_songs.view.*

class AllSongViewHolder(val view:View) : RecyclerView.ViewHolder(view), PopupMenu.OnMenuItemClickListener {
    var song:Song?=null

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_play -> {
                Log.d(TAG, javaClass.name + " | menu item play music")
                playMusic()
            }
        }
        return false
    }

    private fun playMusic() {
        song?.also { song ->
            song.path?.also { path ->
                Log.d(TAG, "\t\t\t | start play task... ${view.context}")
                val intent = Intent(view.context, PlayMusicService::class.java)
                intent.putExtra(Key.PATH, path)
                view.context.startService(intent)
            }
        }
    }

    init {
        val popupMenu = PopupMenu(view.context, view.ic_context_menu)
        popupMenu.menuInflater.inflate(R.menu.menu_context_all_songs_item, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(this)

        view.ic_context_menu.setOnClickListener {
            popupMenu.show()
        }
    }

    fun bind(item: Song) {
        song = item

        item.songByteArray?.also { byteArray ->
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            view.img_avatar_music.setImageBitmap(bitmap)
        }

        if(item.songByteArray == null){
            view.img_avatar_music.setImageResource(R.drawable.ic_avatar_music_64)
        }

        view.tv_name_music.text = item.name?:""
        view.tv_description_music.text = item.artist?:""
    }
}
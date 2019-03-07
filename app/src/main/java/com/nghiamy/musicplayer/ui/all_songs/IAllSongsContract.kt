package com.nghiamy.musicplayer.ui.all_songs

import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.ui.IProgressView

interface IAllSongsContract {
    interface View : IProgressView{
        fun onDisplayAllMusics(musicList:ArrayList<Song>)
    }

    interface Presenter {
        fun onResume()
    }
}
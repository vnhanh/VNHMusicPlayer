package com.nghiamy.musicplayer.ui.main_screen

import com.nghiamy.musicplayer.base.model.Song

interface IMainContract {
    interface View{

    }

    interface Presenter{
        fun onCreate()
        fun onGetAllSongs() : ArrayList<Song>
        fun onDestroy()
    }
}
package com.nghiamy.musicplayer.ui.all_songs

interface IAllSongsContract {
    interface View {
        fun onShowProgressing()
        fun onHideProgressing()
        fun onDisplayAllMusics(musicList:ArrayList<HashMap<String,String>>)
    }

    interface Presenter {
        fun onCreate()
    }
}
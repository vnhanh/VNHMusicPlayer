package com.nghiamy.musicplayer.ui.main_screen

import com.nghiamy.musicplayer.base.model.Song

interface IHomeContract {
    interface View{

    }

    interface Presenter{
        fun onCreate()
        fun onDestroy()
    }
}
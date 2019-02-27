package com.nghiamy.musicplayer.ui.home_screen

interface IHomeContract {
    interface View{

    }

    interface Presenter{
        fun onCreate()
        fun onDestroy()
    }
}
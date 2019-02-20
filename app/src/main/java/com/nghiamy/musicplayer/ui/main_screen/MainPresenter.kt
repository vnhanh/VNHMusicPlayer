package com.nghiamy.musicplayer.ui.main_screen

import com.nghiamy.musicplayer.base.database.RealmServiceSong
import com.nghiamy.musicplayer.base.model.Song

class MainPresenter(private val realmService:RealmServiceSong) : IMainContract.Presenter {
    override fun onCreate() {
        val song = Song()
        song.id = "123130123"
        song.name = "Gia tu co do"
        song.author = "Chanh Tin"
        song.artist = "Khanh Ly"
        song.genres = "Que huong"
        song.playlist = "Tru tinh"
        realmService.addNewSong(song)
    }

    override fun onGetAllSongs() : ArrayList<Song>{
        return realmService.getAllSongs()
    }

    override fun onDestroy() {
        realmService.closeRealm()
    }
}
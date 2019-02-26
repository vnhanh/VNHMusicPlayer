package com.nghiamy.musicplayer.ui.main_screen

import android.util.Log
import com.nghiamy.musicplayer.base.common.SongManager
import com.nghiamy.musicplayer.base.database.RealmServiceHomePerformed

class HomePresenter(val view:IHomeContract.View, val songManager : SongManager, val realmServiceHomePerformed: RealmServiceHomePerformed) : IHomeContract.Presenter {
    private val TAG = "LOG"

    override fun onCreate() {
        Log.d(TAG, "scanned all music files: ${realmServiceHomePerformed.checkIfScanedAllMusic()}")
        if(!realmServiceHomePerformed.checkIfScanedAllMusic()){
            val musicList = songManager.getPlaylist()
            Log.d(TAG, "Number music files scanned: ${musicList.size}")
        }
    }

    override fun onDestroy() {

    }
}
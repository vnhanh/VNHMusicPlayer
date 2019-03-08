package com.nghiamy.musicplayer.ui.home_screen

import com.nghiamy.musicplayer.base.common.SongScanner
import com.nghiamy.musicplayer.base.database.RealmServiceHomePerformed

class HomePresenter(val view:IHomeContract.View, val songScanner : SongScanner, val realmServiceHomePerformed: RealmServiceHomePerformed) : IHomeContract.Presenter {
    private val TAG = "LOG"

    override fun onCreate() {

    }

    override fun onDestroy() {

    }
}
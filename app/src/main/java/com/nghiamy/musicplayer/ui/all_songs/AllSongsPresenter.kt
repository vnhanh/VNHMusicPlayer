package com.nghiamy.musicplayer.ui.all_songs

import android.annotation.SuppressLint
import android.media.MediaMetadataRetriever
import android.util.Log
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.common.Constant.Companion.SONG_PATH
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.common.ConstantSharedPreference.Companion.SCANNED_ALL_MUSIC_FILES
import com.nghiamy.musicplayer.base.common.SongManager
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences

class AllSongsPresenter(
    val view:IAllSongsContract.View,
    val sharedPreferences:CustomizeSharedPreferences,
    val songManager : SongManager) : IAllSongsContract.Presenter {

    @SuppressLint("ResourceType")
    override fun onResume() {
        val isScannedAllMusicFiles = sharedPreferences.getBoolean(SCANNED_ALL_MUSIC_FILES, false)
        if(!isScannedAllMusicFiles){
            view.showProgress(R.string.progress_scanning_all_music_files)
            // need to multithread
            // by using Rxjava
            val allMusicList = songManager.getPlaylist()

            Log.d(TAG, String.format("%s | onResume | scan music files completed...", javaClass.name))

            view.hideProgress()
            view.onDisplayAllMusics(allMusicList)
        }
    }
}
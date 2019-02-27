package com.nghiamy.musicplayer.ui.all_songs

import android.media.MediaMetadataRetriever
import android.util.Log
import com.nghiamy.musicplayer.base.common.Constant.Companion.SONG_PATH
import com.nghiamy.musicplayer.base.common.ConstantSharedPreference.Companion.SCANNED_ALL_MUSIC_FILES
import com.nghiamy.musicplayer.base.common.SongManager
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences

class AllSongsPresenter(
    val view:IAllSongsContract.View,
    val sharedPreferences:CustomizeSharedPreferences,
    val songManager : SongManager) : IAllSongsContract.Presenter {
    private val TAG = "LOG"

    override fun onCreate() {
        val isScannedAllMusicFiles = sharedPreferences.getBoolean(SCANNED_ALL_MUSIC_FILES, false)
        if(!isScannedAllMusicFiles){
            view.onShowProgressing()
            // need to multithread
            // by using Rxjava
            val allMusicList = songManager.getPlaylist()
            val testItem = allMusicList.get(0)
            val testPath = testItem.get(SONG_PATH)
            Log.d(TAG, "testPath: ${testPath}")
            val metadataRetriver = MediaMetadataRetriever()
            metadataRetriver.setDataSource(testPath)

//            view.onDisplayAllMusics(allMusicList)
        }
    }

}
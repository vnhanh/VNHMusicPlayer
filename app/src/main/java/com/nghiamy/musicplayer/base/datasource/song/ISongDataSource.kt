package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.model.Song
import io.reactivex.Flowable
import io.realm.Realm

interface ISongDataSource {
    fun updateSong(song:Song) : Flowable<Boolean>
}
package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.model.Song
import io.reactivex.Observable

interface ISongDataSource {
    fun updateSong(song:Song) : Observable<Boolean>
}
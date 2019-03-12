package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.usecase.SongUtil
import io.reactivex.Observable

class StorageDataSourceSong {
    fun updateSongMetadata(song: Song) : Observable<Boolean> {
        return Observable.just(
            SongUtil.editMetadataSong(song)
        )
    }
}
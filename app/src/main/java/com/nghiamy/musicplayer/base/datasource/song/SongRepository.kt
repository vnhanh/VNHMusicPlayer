package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.model.Song
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable

class SongRepository(val localSource:ISongDataSource, val storageSource:SongStorageSource) : ISongDataSource {

    override fun updateSong(song: Song) : Flowable<Boolean>{
        return Observable.create<Boolean>{
            storageSource.updateSongMetadata(song)
            localSource.updateSong(song)
        }.toFlowable(BackpressureStrategy.BUFFER)
    }
}
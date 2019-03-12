package com.nghiamy.musicplayer.base.datasource.song

import android.util.Log
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.model.Song
import io.reactivex.Observable

class SongRepository(val localSource:ISongDataSource, val storageDataSource:StorageDataSourceSong) : ISongDataSource {

    override fun updateSong(song: Song) : Observable<Boolean> {

//        Observable.create<Boolean>{
//            Log.d(TAG, javaClass.name + " | updateSong | song: ${song.title} | in RxJava2")
//            storageDataSource.updateSongMetadata(song)
//            localSource.updateSong(song)
//        }

        return Observable.zip(
            storageDataSource.updateSongMetadata(song),
            localSource.updateSong(song),
            io.reactivex.functions.BiFunction { t1, t2 ->
                t1 && t2
            }
        )
    }
}
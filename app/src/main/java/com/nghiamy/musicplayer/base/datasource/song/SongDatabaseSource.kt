package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.database.RealmServiceSong
import com.nghiamy.musicplayer.base.model.Song
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.realm.Realm

class SongDatabaseSource(val songService:RealmServiceSong) : ISongDataSource {

    override fun updateSong(song: Song) : Flowable<Boolean> {

        return Observable.create<Boolean>{
                val onSuccess = object : Realm.Transaction.OnSuccess{
                    override fun onSuccess() {
                        it.onNext(true)
                    }
                }

                val onError = object : Realm.Transaction.OnError {
                    override fun onError(error: Throwable) {
                        it.onError(error)
                    }

                }
                songService.updateSong(song, onSuccess = onSuccess, onError = onError)
            }
            .toFlowable(BackpressureStrategy.BUFFER)
    }
}
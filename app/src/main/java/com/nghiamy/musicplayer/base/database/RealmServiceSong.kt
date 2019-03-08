package com.nghiamy.musicplayer.base.database

import com.nghiamy.musicplayer.base.model.Song
import io.realm.Realm

class RealmServiceSong(realm:Realm) : BaseRealmService(realm){

    fun addNewSong(newSong:Song) {
        realm.beginTransaction()
        realm.copyToRealm(newSong)
        realm.commitTransaction()
    }

    fun updateSong(song:Song, onSuccess: Realm.Transaction.OnSuccess, onError:Realm.Transaction.OnError){
        val transaction = object: Realm.Transaction{
            override fun execute(realm: Realm) {
                realm.where(Song::class.java).equalTo(Song.KEY.ID.value, song.id).findFirst()?.also { _song ->
                    _song.title = song.title
                    _song.artist = song.artist
                    _song.author = song.author
                    _song.albumn = song.albumn
                    _song.genres = song.genres
                }
            }

        }

        realm.executeTransactionAsync(transaction, onSuccess, onError)
    }

    fun getAllSongs():ArrayList<Song>{
        val list = ArrayList<Song>()
        realm.beginTransaction()
        val results = realm.where(Song::class.java).findAll()
        realm.commitTransaction()

        list.addAll(realm.copyFromRealm(results))

        return list
    }
}
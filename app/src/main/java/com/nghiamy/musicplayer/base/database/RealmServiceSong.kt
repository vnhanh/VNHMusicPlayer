package com.nghiamy.musicplayer.base.database

import com.nghiamy.musicplayer.base.model.Song
import io.realm.Realm

class RealmServiceSong(private val realm:Realm) {
    fun closeRealm(){
        realm.close()
    }

    fun addNewSong(newSong:Song) {
        realm.beginTransaction()
        realm.copyToRealm(newSong)
        realm.commitTransaction()
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
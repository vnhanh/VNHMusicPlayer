package com.nghiamy.musicplayer.base.database

import com.nghiamy.musicplayer.base.model.HomePerformed
import io.realm.Realm
import io.realm.RealmQuery

class RealmServiceHomePerformed(realm:Realm) : BaseRealmService(realm) {
    fun checkIfScanedAllMusic() : Boolean{
        val query:RealmQuery<HomePerformed> = realm.where(HomePerformed::class.java)
        return query.equalTo("scannedAllMusicFiles", true).findFirst() != null
    }
}
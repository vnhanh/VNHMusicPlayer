package com.nghiamy.musicplayer.base.database

import io.realm.Realm

abstract class BaseRealmService(protected val realm:Realm) {
    protected fun closeRealm(){
        realm.close()
    }
}
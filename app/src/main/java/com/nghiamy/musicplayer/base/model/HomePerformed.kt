package com.nghiamy.musicplayer.base.model

import io.realm.RealmObject

/**
 * Store all track activities's user
 */
open class HomePerformed() : RealmObject() {
    var scannedAllMusicFiles = false
}
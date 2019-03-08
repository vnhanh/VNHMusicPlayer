package com.nghiamy.musicplayer.base.model

import android.support.design.internal.NavigationMenu
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField
import io.realm.annotations.Required
import java.util.*

open class Song : RealmObject(){
    @PrimaryKey
    var id:String=""

    var title:String?=null
    var author:String?=null
    var genres:String?=null
    var playlist:String?=null
    var albumn:String?=null
    var artist:String?=null
    var path:String?=null
    var picBytes:ByteArray?=null

    enum class KEY(val value:String){
        ID("id"),
        TITLE("title"),
        AUTHOR("author"),
        GENRES("genres"),
        PLAYLIST("playlist"),
        ALBUMN("albumn"),
        ARTIST("artist"),
        PATH("path"),
        PIC_BYTES("picBytes")
    }
}
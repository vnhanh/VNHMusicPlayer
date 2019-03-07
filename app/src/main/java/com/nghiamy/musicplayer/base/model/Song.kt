package com.nghiamy.musicplayer.base.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Song : RealmObject(){
    @PrimaryKey
    var id:String=""

    var name:String?=null
    var author:String?=null
    var genres:String?=null
    var playlist:String?=null
    var artist:String?=null
    var path:String?=null
    var songByteArray:ByteArray?=null
}
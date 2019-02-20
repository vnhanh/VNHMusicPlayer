package com.nghiamy.musicplayer.base.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Song : RealmObject(){
    @PrimaryKey
    var id:String=""
    @Required
    var name:String=""
    var author:String=""
    var genres:String=""
    var playlist:String=""
    var artist:String=""
}
package com.nghiamy.musicplayer

import io.realm.DynamicRealm
import io.realm.RealmMigration
import java.util.*

class MyRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
//        var _oldVersion = oldVersion
//        val schema = realm.schema
//        if(_oldVersion == 0L){
//            schema.get("Song")?.apply {
//                addField("id", String::class.java)
//                addField("name", String::class.java)
//                addField("author", String::class.java)
//                addField("genres", String::class.java)
//                addField("playlist", String::class.java)
//                addField("artist", String::class.java)
//                _oldVersion++
//            }
//        }
    }
}
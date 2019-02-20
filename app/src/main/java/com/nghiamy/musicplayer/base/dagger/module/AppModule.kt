package com.nghiamy.musicplayer.base.dagger.module

import android.content.Context
import com.nghiamy.musicplayer.base.MyApplication
import com.nghiamy.musicplayer.base.database.RealmServiceSong
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app:MyApplication) : Context = app

    // I plan to break this class down to more part, 1 part totally only provide Realm services
    @Provides
    @Singleton
    fun provideRealm():Realm = Realm.getDefaultInstance()

    @Provides
    @Singleton
    fun provideRealmServiceSong(realm:Realm):RealmServiceSong = RealmServiceSong(realm)
}
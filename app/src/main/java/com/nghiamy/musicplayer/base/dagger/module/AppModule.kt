package com.nghiamy.musicplayer.base.dagger.module

import android.content.Context
import com.nghiamy.musicplayer.base.MyApplication
import com.nghiamy.musicplayer.base.common.ConstantSharedPreference.Companion.SHAREDPREF_NAME
import com.nghiamy.musicplayer.base.common.SongScanner
import com.nghiamy.musicplayer.base.database.RealmServiceHomePerformed
import com.nghiamy.musicplayer.base.database.RealmServiceSong
import com.nghiamy.musicplayer.base.datasource.song.SongRepository
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app:MyApplication) : Context = app

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): CustomizeSharedPreferences = CustomizeSharedPreferences(context, SHAREDPREF_NAME)

    @Provides
    @Singleton
    fun provideSongManager(context: Context) : SongScanner = SongScanner(context)

    // I plan to break this class down to more part, 1 part totally only provide Realm services
    @Provides
    @Singleton
    fun provideRealm():Realm = Realm.getDefaultInstance()
}
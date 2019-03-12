package com.nghiamy.musicplayer.base.dagger.module

import com.nghiamy.musicplayer.base.database.RealmServiceHomePerformed
import com.nghiamy.musicplayer.base.database.RealmServiceSong
import com.nghiamy.musicplayer.base.datasource.song.LocalDataSourceSong
import com.nghiamy.musicplayer.base.datasource.song.SongRepository
import com.nghiamy.musicplayer.base.datasource.song.StorageDataSourceSong
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Provides
    @Singleton
    fun provideRealmServiceSong(realm: Realm):RealmServiceSong = RealmServiceSong(realm)

    @Provides
    @Singleton
    fun provideRealmServiceHomePerformed(realm: Realm): RealmServiceHomePerformed = RealmServiceHomePerformed(realm)

    @Provides
    @Singleton
    fun provideSongLocalDataSource(service:RealmServiceSong) : LocalDataSourceSong = LocalDataSourceSong(service)

    @Provides
    @Singleton
    fun provideStorageDataSourceSong() : StorageDataSourceSong = StorageDataSourceSong()

    @Provides
    @Singleton
    fun provideSongRepository(localDataSourceSong:LocalDataSourceSong, storageDataDataSourceSong:StorageDataSourceSong) : SongRepository
            = SongRepository(localDataSourceSong, storageDataDataSourceSong)
}
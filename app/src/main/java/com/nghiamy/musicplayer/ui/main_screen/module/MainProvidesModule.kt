package com.nghiamy.musicplayer.ui.main_screen.module

import com.nghiamy.musicplayer.base.dagger.scope.PerActivity
import com.nghiamy.musicplayer.base.database.RealmServiceSong
import com.nghiamy.musicplayer.ui.main_screen.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainProvidesModule {
    @Provides
    @PerActivity
    fun providePresenter(realmService: RealmServiceSong) : MainPresenter = MainPresenter(realmService)
}
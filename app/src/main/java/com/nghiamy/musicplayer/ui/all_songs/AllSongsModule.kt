package com.nghiamy.musicplayer.ui.all_songs

import com.nghiamy.musicplayer.base.common.SongManager
import com.nghiamy.musicplayer.base.dagger.scope.PerFragment
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AllSongsModule {
    // Provides
    companion object {
        @Provides
        @Singleton
        @PerFragment
        fun providePresenter(view:IAllSongsContract.View,
                             preferences: CustomizeSharedPreferences,
                             songManager: SongManager) : IAllSongsContract.Presenter = AllSongsPresenter(view, preferences, songManager)
    }

    // Binds
    @Binds
    @PerFragment
    abstract fun bindView(fragment:AllSongsFragment) : IAllSongsContract.View

}
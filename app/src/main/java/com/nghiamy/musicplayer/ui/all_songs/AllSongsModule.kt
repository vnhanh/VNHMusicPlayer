package com.nghiamy.musicplayer.ui.all_songs

import com.nghiamy.musicplayer.base.common.SongScanner
import com.nghiamy.musicplayer.base.dagger.scope.PerFragment
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AllSongsModule {
    // Provides
    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerFragment
        fun providePresenter(view:IAllSongsContract.View,
                             preferences: CustomizeSharedPreferences,
                             songScanner: SongScanner) : IAllSongsContract.Presenter = AllSongsPresenter(view, preferences, songScanner)
    }

    // Binds
    @Binds
    @PerFragment
    abstract fun bindView(fragment:AllSongsFragment) : IAllSongsContract.View

}
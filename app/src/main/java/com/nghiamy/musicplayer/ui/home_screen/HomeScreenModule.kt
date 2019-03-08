package com.nghiamy.musicplayer.ui.home_screen

import com.nghiamy.musicplayer.base.common.SongScanner
import com.nghiamy.musicplayer.base.dagger.scope.PerActivity
import com.nghiamy.musicplayer.base.database.RealmServiceHomePerformed
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class HomeScreenModule {
    @Binds
    @PerActivity
    abstract fun bindView(activity:HomeActivity) : IHomeContract.View

    @Binds
    @PerActivity
    abstract fun bindPresenter(presenter:HomePresenter) : IHomeContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerActivity
        fun providePresenter(view:IHomeContract.View, songScanner: SongScanner,
                             realmServiceHomePerformed: RealmServiceHomePerformed) : HomePresenter = HomePresenter(view, songScanner, realmServiceHomePerformed)
    }
}
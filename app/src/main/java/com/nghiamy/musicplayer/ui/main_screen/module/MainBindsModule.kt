package com.nghiamy.musicplayer.ui.main_screen.module

import com.nghiamy.musicplayer.base.dagger.scope.PerActivity
import com.nghiamy.musicplayer.ui.main_screen.IMainContract
import com.nghiamy.musicplayer.ui.main_screen.MainActivity
import com.nghiamy.musicplayer.ui.main_screen.MainPresenter
import dagger.Binds
import dagger.Module

@Module(includes = [MainProvidesModule::class])
abstract class MainBindsModule {
    @Binds
    @PerActivity
    abstract fun bindView(activity:MainActivity) : IMainContract.View

    @Binds
    @PerActivity
    abstract fun bindPresenter(presenter:MainPresenter) : IMainContract.Presenter
}
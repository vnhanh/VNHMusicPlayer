package com.nghiamy.musicplayer.base.dagger.module

import android.app.Application
import com.nghiamy.musicplayer.base.MyApplication
import com.nghiamy.musicplayer.base.dagger.scope.PerActivity
import com.nghiamy.musicplayer.ui.main_screen.MainActivity
import com.nghiamy.musicplayer.ui.main_screen.module.MainBindsModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityModule {
    @Binds
    @Singleton
    abstract fun bindApplication(app:MyApplication) : Application

    @ContributesAndroidInjector(modules = [MainBindsModule::class])
    @PerActivity
    abstract fun bindMainActivity() : MainActivity
}
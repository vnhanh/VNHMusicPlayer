package com.nghiamy.musicplayer.base

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.nghiamy.musicplayer.base.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    @Inject lateinit var activityInjector:DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector:DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        DaggerAppComponent.builder().application(this).build().inject(this)
        super.onCreate()
        Realm.init(this)
        val realmConfig =
            RealmConfiguration.Builder()
                .name("musicplayer.realm")
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    override fun onTerminate() {
        Realm.getDefaultInstance().close()
        super.onTerminate()
    }
}